package com.plex.androidsdk.httpdatasources;

/**
 * Interface for making Http request to data source server.
 */
public interface IDataSourceConnector {
    void execute(int dataSourceKey, HttpDataSourceCredentials credentials, String serverName, boolean useTestServer, String jsonRequest, IDataSourceConnectorCallback callback);
}
