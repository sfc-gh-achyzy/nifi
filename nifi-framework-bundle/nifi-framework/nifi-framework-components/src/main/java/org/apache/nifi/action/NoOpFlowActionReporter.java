package org.apache.nifi.action;

import java.util.Collection;

public class NoOpFlowActionReporter implements FlowActionReporter {

    @Override
    public void reportFlowActions(Collection<FlowAction> actions) {

    }

}
