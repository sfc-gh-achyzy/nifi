package org.apache.nifi.action;

import java.util.Collection;

public interface FlowActionReporter {
    default void onConfigured(FlowActionReporterConfigurationContext context) throws FlowActionReporterCreationException{}
    void reportFlowActions(Collection<FlowAction> actions);
    default void preDestruction(){}
}
