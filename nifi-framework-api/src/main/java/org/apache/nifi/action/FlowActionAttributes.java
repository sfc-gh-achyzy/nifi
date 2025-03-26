package org.apache.nifi.action;

public interface FlowActionAttributes {

    enum ACTION implements FlowActionAttribute {
        ID("id"),
        TIMESTAMP("timestamp"),
        USER_IDENTITY("userIdentity"),
        SOURCE_ID("sourceId"),
        SOURCE_TYPE("sourceType"),
        OPERATION("operation"),
        ;

        private static final String PREFIX = "action.";
        private final String key;

        ACTION(String key) {
            this.key = key;
        }

        @Override
        public String key() {
            return PREFIX + key;
        }
    }

    enum ACTION_DETAILS implements FlowActionAttribute {
        NAME("name"),
        SOURCE_ID("sourceId"),
        SOURCE_TYPE("sourceType"),
        DESTINATION_ID("destinationId"),
        DESTINATION_TYPE("destinationType"),
        RELATIONSHIP("relationship"),
        GROUP_ID("groupId"),
        PREVIOUS_GROUP_ID("previousGroupId"),
        END_DATE("endDate"),
        ;

        private static final String PREFIX = "actionDetails.";
        private final String key;

        ACTION_DETAILS(String key) {
            this.key = key;
        }

        @Override
        public String key() {
            return PREFIX + key;
        }
    }

    enum COMPONENT_DETAILS implements FlowActionAttribute {
        TYPE("type"),
        URI("uri"),
        ;

        private static final String PREFIX = "componentDetails.";
        private final String key;

        COMPONENT_DETAILS(String key) {
            this.key = key;
        }

        @Override
        public String key() {
            return PREFIX + key;
        }
    }

}
