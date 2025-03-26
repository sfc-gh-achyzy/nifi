package org.apache.nifi.action;

import com.google.common.collect.Maps;
import org.apache.nifi.action.component.details.ComponentDetails;
import org.apache.nifi.action.component.details.ExtensionDetails;
import org.apache.nifi.action.component.details.RemoteProcessGroupDetails;
import org.apache.nifi.action.details.*;

import java.util.Map;

public class ActionToFlowActionConverter implements ActionConverter {

    @Override
    public FlowAction convert(Action action) {
        Map<String, String> properties = Maps.newHashMap();
        properties.putAll(actionProperties(action));
        properties.putAll(actionDetailsProperties(action.getActionDetails()));
        properties.putAll(componentDetailsProperties(action.getComponentDetails()));
        return new StandardFlowAction(properties);
    }

    private Map<String, String> actionProperties(Action action) {
        return Map.of(
                "id", String.valueOf(action.getId()),
                "timestamp", action.getTimestamp().toInstant().toString(),
                "userIdentity", action.getUserIdentity(),
                "sourceId", action.getSourceId(),
                "sourceType", action.getSourceType().name(),
                "operation", action.getOperation().name()
        );
    }

    private Map<String, String> actionDetailsProperties(ActionDetails actionDetails) {
        return switch (actionDetails) {
            case ConfigureDetails configureDetails -> Map.of(
                    "name", configureDetails.getName()

            );
            case ConnectDetails connectDetails -> Map.of(
                    "sourceId", connectDetails.getSourceId(),
                    "sourceType", connectDetails.getSourceType().name(),
                    "destinationId", connectDetails.getDestinationId(),
                    "destinationType", connectDetails.getDestinationType().name(),
                    "relationship", connectDetails.getRelationship()

            );
            case MoveDetails moveDetails -> Map.of(
                    "groupId", moveDetails.getGroupId(),
                    "previousGroupId", moveDetails.getPreviousGroupId()

            );
            case PurgeDetails purgeDetails -> Map.of(
                    "endDate", purgeDetails.getEndDate().toInstant().toString()

            );
            case null, default -> Map.of();
        };
    }

    private Map<String, String> componentDetailsProperties(ComponentDetails componentDetails) {
        return switch (componentDetails) {
            case ExtensionDetails extensionDetails -> Map.of(
                    "type", extensionDetails.getType()

            );
            case RemoteProcessGroupDetails remoteProcessGroupDetails -> Map.of(
                    "uri", remoteProcessGroupDetails.getUri()

            );
            case null, default -> Map.of();
        };
    }
}
