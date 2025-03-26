package org.apache.nifi.action;

import java.util.Map;

public class StandardFlowAction implements FlowAction {

    private Map<String, String> properties;

    public StandardFlowAction() {
    }

    public StandardFlowAction(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, String> getProperties() {
        return Map.copyOf(properties);
    }

    @Override
    public void putAllProperties(Map<String, String> properties) {
        this.properties.putAll(properties);
    }

    @Override
    public String getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public void putProperty(String key, String value) {
        properties.put(key, value);
    }
}
