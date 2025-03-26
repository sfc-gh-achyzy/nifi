package org.apache.nifi.action;

public interface ActionConverter {

    FlowAction convert(Action action);
}
