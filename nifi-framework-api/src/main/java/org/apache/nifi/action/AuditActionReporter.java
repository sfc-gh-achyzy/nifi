package org.apache.nifi.action;

import java.util.Collection;

public interface AuditActionReporter {
    void reportActions(Collection<Action> actions);
}
