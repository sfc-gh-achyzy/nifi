package org.apache.nifi.action;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.util.Map;

public class StandardFlowActionReporterConfigurationContext implements FlowActionReporterConfigurationContext {
    private final Map<String, String> properties;
    private final SSLContext sslContext;
    private final X509TrustManager trustManager;

    public StandardFlowActionReporterConfigurationContext(final Map<String, String> properties, final SSLContext sslContext, X509TrustManager trustManager) {
        this.properties = properties;
        this.sslContext = sslContext;
        this.trustManager = trustManager;
    }

    @Override
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public SSLContext getSSLContext() {
        return sslContext;
    }

    @Override
    public X509TrustManager getTrustManager() {
        return trustManager;
    }
}
