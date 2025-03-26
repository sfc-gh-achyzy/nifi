package org.apache.nifi.action;

import java.util.Map;

public interface FlowAction {
    Map<String,String> getAttributes();
    void putAllAttributes(Map<String,String> properties);
    String getAttribute(String key);
    void putAttribute(String key, String value);
}
