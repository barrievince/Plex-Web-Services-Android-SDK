package com.plex.androidsdk.httpdatasources;

/**
 * Interface for making Http request to data source server.
 */

public interface IHttpConnector {
//    HttpDataSourceResult execute(HttpDataSourceCredentials credentials, URL url, String jsonRequest);
    void execute(int dataSourceKey, HttpDataSourceCredentials credentials, String serverName, boolean useTestServer, String jsonRequest, IHttpConnectorCallback callback);
}
