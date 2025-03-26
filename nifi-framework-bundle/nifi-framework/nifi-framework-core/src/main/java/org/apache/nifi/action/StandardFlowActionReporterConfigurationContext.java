package org.apache.nifi.action;

import javax.net.ssl.SSLContext;
import java.util.Map;

public class StandardFlowActionReporterConfigurationContext implements FlowActionReporterConfigurationContext {
    private final Map<String, String> properties;
    private final SSLContext sslContext;

    public StandardFlowActionReporterConfigurationContext(final Map<String, String> properties, final SSLContext sslContext) {
        this.properties = properties;
        this.sslContext = sslContext;
    }

    @Override
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public SSLContext getSSLContext() {
        return sslContext;
    }
}
