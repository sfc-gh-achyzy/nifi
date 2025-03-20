package org.apache.nifi.action;

import java.util.Collection;

public class NoOpAuditActionReporter implements AuditActionReporter{
    @Override
    public void reportActions(Collection<Action> actions) {

    }
}
