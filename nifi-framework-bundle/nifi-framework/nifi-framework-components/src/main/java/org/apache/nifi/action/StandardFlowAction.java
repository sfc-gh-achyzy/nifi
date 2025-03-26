package org.apache.nifi.action;

import java.util.Map;

public class StandardFlowAction implements FlowAction {

    private Map<String, String> attributes;

    public StandardFlowAction() {
    }

    public StandardFlowAction(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, String> getAttributes() {
        return Map.copyOf(attributes);
    }

    @Override
    public void putAllAttributes(Map<String, String> properties) {
        this.attributes.putAll(properties);
    }

    @Override
    public String getAttribute(String key) {
        return attributes.get(key);
    }

    @Override
    public void putAttribute(String key, String value) {
        attributes.put(key, value);
    }
}
