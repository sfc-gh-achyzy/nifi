package org.apache.nifi.action;

import java.util.Collection;

public interface AuditActionReporter extends AutoCloseable {
    default void init(){}
    void reportActions(Collection<Action> actions);
    default void close(){}
}
