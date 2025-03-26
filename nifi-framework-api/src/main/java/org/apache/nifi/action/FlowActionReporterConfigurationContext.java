package org.apache.nifi.action;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.util.Map;

/**
 * A context that will be passed to the reporter in order to obtain configuration.
 */
public interface FlowActionReporterConfigurationContext {
    /**
     * Retrieves all properties the context currently understands regardless
     * of whether a value has been set for them or not. If no value is present
     * then its value is null and thus any registered default for the property
     * descriptor applies.
     *
     * @return Map of all properties
     */
    Map<String, String> getProperties();

    /**
     * Retrieves SSLContext if set
     * @return SSLContext
     */
    SSLContext getSSLContext();

    /**
     * Retrieves the trust manager if set
     * @return X509TrustManager
     */
    X509TrustManager getTrustManager();
}
