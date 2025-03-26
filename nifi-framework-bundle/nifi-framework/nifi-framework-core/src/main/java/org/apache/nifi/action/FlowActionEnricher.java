package org.apache.nifi.action;

import org.apache.nifi.authorization.user.NiFiUser;
import org.apache.nifi.authorization.user.NiFiUserUtils;

import java.util.Map;

public class FlowActionEnricher {
    FlowAction enrich(FlowAction action) {

        NiFiUser user = NiFiUserUtils.getNiFiUser();
        if (user == null) {
            return action;
        }

        Map<String, String> attributes = Map.of(
                FlowActionAttributes.HTTP.CLIENT_ADDRESS.key(), user.getClientAddress(),
                FlowActionAttributes.HTTP.USER_AGENT.key(), user.getUserAgent()
        );
        action.putAllAttributes(attributes);

        return action;
    }

}
