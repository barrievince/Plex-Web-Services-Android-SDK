package com.plex.androidsdk.httpdatasources;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


// TODO: Decide if to extend from Connector or use interface IHttpConnector

public abstract class DataSource implements IHttpConnectorCallback {

    private IDataSourceCallback _dataSourceCallback;
    private IHttpConnector _connector;
    private HttpDataSourceCredentials _credentials;
    private String _serverName;
    private boolean _useTestServer;

    public DataSource(IDataSourceCallback iDataSourceCallback, HttpDataSourceCredentials credentials, String serverName, boolean useTestServer) {
        this(iDataSourceCallback, credentials, serverName, useTestServer, new HttpConnector());
    }

    public DataSource(IDataSourceCallback iDataSourceCallback, HttpDataSourceCredentials credentials, String serverName, boolean useTestServer, IHttpConnector connector) {
        _dataSourceCallback = iDataSourceCallback;
        _credentials = credentials;
        _serverName = serverName;
        _useTestServer = useTestServer;
        _connector = connector;
    }


    /**
     * Execute the task
     */
    public void execute() {
        _connector.execute(this.getDataSourceKey(), _credentials, _serverName, _useTestServer, this.getJsonRequest(), this);
    }

    /**
     * Helper method to convert the input parameters into JSON.
     *
     * @return The JSON request
     */
    private String getJsonRequest() {
        String jsonRequest = null;
        IBaseInput baseInput = this.getBaseInput();

        if (baseInput != null) {
            Gson gson = new Gson();
            BaseInputs baseInputs = new BaseInputs(baseInput);
            jsonRequest = gson.toJson(baseInputs);
        }

        return jsonRequest;
    }

    public void onHttpDataSourceComplete(HttpDataSourceResult result) {
        DataSourceResult dsResult;

        if (result.getHTTPResponseCode() == 200) {
            dsResult = this.parseJsonResponse(result.getJsonResponse());
        } else {
            dsResult = this.parseJsonError(result.getJsonResponse());
        }

        if (result.getException() != null) {
            dsResult.setException(result.getException());
        }

        _dataSourceCallback.onDataSourceComplete(dsResult);
    }

    public void onProgressUpdate(int progressCode) {

    }

    public void onFinish() {

    }


    /**
     * Parses the JSON returned from the http data source call.
     *
     * @param jsonResponse The JSON string.
     */
    private DataSourceResult parseJsonResponse(String jsonResponse) {

        DataSourceResult dsResult = new DataSourceResult();

        JsonObject jsonTree = new JsonParser().parse(jsonResponse).getAsJsonObject();
        if (jsonTree.isJsonObject()) {

            JsonObject jsonObject = jsonTree.getAsJsonObject();

            // Convert any outputs to an instance object
            if (jsonObject.has("outputs")) {

                JsonElement outputsElement = jsonObject.get("outputs");
                dsResult.setOutputs(new Gson().fromJson(outputsElement, BaseOutputs.class));
            }

            // Convert any tables to instance objects
            if (jsonObject.has("tables")) {
                JsonArray tablesArray = jsonObject.getAsJsonArray("tables");
                // tablesArray.size should always equal 1, as we will never actually return multiple result sets. The code works under that premise.
                if (tablesArray.size() > 0) {
                    Table resultTable = new Table();

                    // Array item 0 will be the result set, so get is as a JsonObject
                    JsonObject tableObject = tablesArray.get(0).getAsJsonObject();

                    // Put the column names into a List
                    if (tableObject.has("columns")) {
                        Type listType = new TypeToken<List<String>>() {
                        }.getType();

                        List<String> columns = new Gson().fromJson(tableObject.get("columns"), listType);
                        resultTable.setColumns(columns);
                    }

                    // Parse the table rows
                    if (tableObject.has("rows")) {
                        JsonArray rowsArray = tableObject.get("rows").getAsJsonArray();

                        for (int i = 0; i < rowsArray.size(); ++i) {
                            JsonArray rowArray = rowsArray.get(i).getAsJsonArray();
                            BaseRow baseRow = parseRow(rowArray);
                            if (baseRow != null) {
                                resultTable.addRow(baseRow);
                            }
                        }
                    }

                    if (tableObject.has("rowLimitExceeded")) {
                        resultTable.setRowLimitExceeded(tableObject.get("rowLimitExceeded").getAsBoolean());
                    }

                    dsResult.setTable(resultTable);
                }
            }

            // Every result will have "transaction no", so we don't even test. Just read it.
            dsResult.setTransactionNo(jsonObject.get("transactionNo").getAsString());
        }

        return dsResult;
    }

    private DataSourceResult parseJsonError(String jsonResponse) {
        DataSourceResult dsResult = new DataSourceResult();
        dsResult.setHttpDataSourceErrors(new Gson().fromJson(jsonResponse, HttpDataSourceErrors.class));

        return dsResult;
    }




    /* ****** ABSTRACT METHODS ****** **/

    /**
     * Get the Plex data source key for the http data source call.
     */
    protected abstract int getDataSourceKey();

    /**
     * Input parameters for the data source.
     * The returned instance should extend BaseInput and add parameters, with field names that match the parameter tags.
     *
     * @return An extension of BaseInput that contains the input parameters.
     */
    protected abstract IBaseInput getBaseInput();

    /**
     * Output parameters of the http data source.
     * Will return null if no output is expected.
     *
     * @return An extension of BaseOutputs that contains the fields of the output parameters.
     */
    protected abstract BaseOutputs getBaseOutput();

    /**
     * Parses a row entry for the returned JSON.
     *
     * @param rowArray A row entry in the returned JSON.
     */
    protected abstract BaseRow parseRow(JsonArray rowArray);
}
