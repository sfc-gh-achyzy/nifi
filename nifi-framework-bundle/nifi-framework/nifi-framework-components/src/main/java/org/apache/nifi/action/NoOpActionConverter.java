package org.apache.nifi.action;

public class NoOpActionConverter implements ActionConverter {

    @Override
    public FlowAction convert(Action action) {
        return new EmptyFlowAction();
        }
}
