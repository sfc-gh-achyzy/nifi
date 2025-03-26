package org.apache.nifi.action;

import java.util.Map;

public interface FlowAction {
    Map<String,String> getProperties();
    void putAllProperties(Map<String,String> properties);
    String getProperty(String key);
    void putProperty(String key, String value);
}
