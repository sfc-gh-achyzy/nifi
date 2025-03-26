package org.apache.nifi.action;

import java.util.Map;

public class EmptyFlowAction implements FlowAction{
    @Override
    public Map<String, String> getAttributes() {
        return Map.of();
    }

    @Override
    public void putAllAttributes(Map<String, String> properties) {

    }

    @Override
    public String getAttribute(String key) {
        return "";
    }

    @Override
    public void putAttribute(String key, String value) {

    }
}
