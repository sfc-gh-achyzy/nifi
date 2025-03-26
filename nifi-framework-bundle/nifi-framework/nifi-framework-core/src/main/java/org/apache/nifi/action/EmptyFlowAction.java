package org.apache.nifi.action;

import java.util.Map;

public class EmptyFlowAction implements FlowAction{
    @Override
    public Map<String, String> getProperties() {
        return Map.of();
    }

    @Override
    public void putAllProperties(Map<String, String> properties) {

    }

    @Override
    public String getProperty(String key) {
        return "";
    }

    @Override
    public void putProperty(String key, String value) {

    }
}
