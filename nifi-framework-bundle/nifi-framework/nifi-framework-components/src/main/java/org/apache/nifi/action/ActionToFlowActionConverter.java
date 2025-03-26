package org.apache.nifi.action;

import org.apache.nifi.action.component.details.ComponentDetails;
import org.apache.nifi.action.component.details.ExtensionDetails;
import org.apache.nifi.action.component.details.RemoteProcessGroupDetails;
import org.apache.nifi.action.details.ActionDetails;
import org.apache.nifi.action.details.ConfigureDetails;
import org.apache.nifi.action.details.ConnectDetails;
import org.apache.nifi.action.details.MoveDetails;
import org.apache.nifi.action.details.PurgeDetails;

import java.util.HashMap;
import java.util.Map;

public class ActionToFlowActionConverter implements ActionConverter {

    @Override
    public FlowAction convert(Action action) {
        Map<String, String> attributes = new HashMap<>();
        actionAttributes(action, attributes);
        actionDetailsAttributes(action.getActionDetails(), attributes);
        componentDetailsProperties(action.getComponentDetails(), attributes);
        return new StandardFlowAction(attributes);
    }

    private void actionAttributes(Action action, Map<String, String> attributes) {
        attributes.put(FlowActionAttributes.ACTION.ID.key(), action.getId() == null ? "" : String.valueOf(action.getId()));
        attributes.put(FlowActionAttributes.ACTION.TIMESTAMP.key(), action.getTimestamp() == null ? "" : action.getTimestamp().toInstant().toString());
        attributes.put(FlowActionAttributes.ACTION.USER_IDENTITY.key(), action.getUserIdentity() == null ? "" : action.getUserIdentity());
        attributes.put(FlowActionAttributes.ACTION.SOURCE_ID.key(), action.getSourceId() == null ? "" : action.getSourceId());
        attributes.put(FlowActionAttributes.ACTION.SOURCE_TYPE.key(), action.getSourceType() == null ? "" : action.getSourceType().name());
        attributes.put(FlowActionAttributes.ACTION.OPERATION.key(), action.getOperation() == null ? "" : action.getOperation().name());
    }

    private void actionDetailsAttributes(ActionDetails actionDetails, Map<String, String> attributes) {
        switch (actionDetails) {
            case ConfigureDetails configureDetails -> attributes.put(
                    FlowActionAttributes.ACTION_DETAILS.NAME.key(), configureDetails.getName() == null ? "" : configureDetails.getName());

            case ConnectDetails connectDetails -> {
                attributes.put(FlowActionAttributes.ACTION_DETAILS.SOURCE_ID.key(), connectDetails.getSourceId() == null ? "" : connectDetails.getSourceId());
                attributes.put(FlowActionAttributes.ACTION_DETAILS.SOURCE_TYPE.key(), connectDetails.getSourceType() == null ? "" : connectDetails.getSourceType().name());
                attributes.put(FlowActionAttributes.ACTION_DETAILS.DESTINATION_ID.key(), connectDetails.getDestinationId() == null ? "" : connectDetails.getDestinationId());
                attributes.put(FlowActionAttributes.ACTION_DETAILS.DESTINATION_TYPE.key(), connectDetails.getDestinationType() == null ? "" : connectDetails.getDestinationType().name());
                attributes.put(FlowActionAttributes.ACTION_DETAILS.RELATIONSHIP.key(), connectDetails.getRelationship() == null ? "" : connectDetails.getRelationship());
            }
            case MoveDetails moveDetails -> {
                attributes.put(FlowActionAttributes.ACTION_DETAILS.GROUP_ID.key(), moveDetails.getGroupId() == null ? "" : moveDetails.getGroupId());
                attributes.put(FlowActionAttributes.ACTION_DETAILS.PREVIOUS_GROUP_ID.key(), moveDetails.getPreviousGroupId() == null ? "" : moveDetails.getPreviousGroupId());
            }

            case PurgeDetails purgeDetails ->
                    attributes.put(FlowActionAttributes.ACTION_DETAILS.END_DATE.key(), purgeDetails.getEndDate() == null ? "" : purgeDetails.getEndDate().toInstant().toString());
            case null, default -> {
            }
        }
    }

    private void componentDetailsProperties(ComponentDetails componentDetails, Map<String, String> attributes) {
        switch (componentDetails) {
            case ExtensionDetails extensionDetails ->
                    attributes.put(FlowActionAttributes.COMPONENT_DETAILS.TYPE.key(), extensionDetails.getType() == null ? "" : extensionDetails.getType());
            case RemoteProcessGroupDetails remoteProcessGroupDetails ->
                    attributes.put(FlowActionAttributes.COMPONENT_DETAILS.URI.key(), remoteProcessGroupDetails.getUri() == null ? "" : remoteProcessGroupDetails.getUri());
            case null, default -> {
            }
        }
        ;
    }
}
