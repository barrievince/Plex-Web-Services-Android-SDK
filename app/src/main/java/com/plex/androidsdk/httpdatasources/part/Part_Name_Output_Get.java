/*
 * Copyright 2019 Plex Systems, Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.plex.androidsdk.httpdatasources.part;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.plex.androidsdk.httpdatasources.BaseOutputs;
import com.plex.androidsdk.httpdatasources.BaseRow;
import com.plex.androidsdk.httpdatasources.DataSource;
import com.plex.androidsdk.httpdatasources.HttpDataSourceCredentials;
import com.plex.androidsdk.httpdatasources.IBaseInput;
import com.plex.androidsdk.httpdatasources.IDataSourceCallback;
import com.plex.androidsdk.httpdatasources.IDataSourceConnector;

/**
 * Data source: Part_Name_Output_get
 */
public class Part_Name_Output_Get extends DataSource {

  private Part_Name_Output_Get.InputParameters _inputParameters = new Part_Name_Output_Get.InputParameters();

  /**
   * {@inheritDoc} Shortcut constructor for production.
   */
  public Part_Name_Output_Get(IDataSourceCallback iDataSourceCallback, HttpDataSourceCredentials credentials, String serverName) {
    this(iDataSourceCallback, credentials, serverName, false);
  }

  /**
   * {@inheritDoc}
   */
  public Part_Name_Output_Get(IDataSourceCallback iDataSourceCallback, HttpDataSourceCredentials credentials, String serverName,
      boolean useTestServer) {
    super(iDataSourceCallback, credentials, serverName, useTestServer);
  }

  /**
   * {@inheritDoc}
   */
  protected Part_Name_Output_Get(IDataSourceCallback iDataSourceCallback, HttpDataSourceCredentials credentials, String serverName,
      boolean useTestServer,
      IDataSourceConnector connector) {
    super(iDataSourceCallback, credentials, serverName, useTestServer, connector);
  }


  @Override
  protected int getDataSourceKey() {
    return 721;
  }

  @Override
  protected IBaseInput getBaseInput() {
    return _inputParameters;
  }

  @Override
  protected BaseOutputs getBaseOutput() {
    return new OutputParameters();
  }

  @Override
  protected BaseRow parseRow(JsonArray rowArray) {
    return null;
  }

  //region INTERNAL CLASSES

  /**
   * Input parameters for data source call. Used by GSON to serialize into JSON.
   */
  private class InputParameters implements IBaseInput {

    @SerializedName("Part_Key")
    private int partKey;

    public int getPartKey() {
      return partKey;
    }

    public void setPartKey(int partKey) {
      this.partKey = partKey;
    }
  }

  private class OutputParameters extends BaseOutputs {
    public String Part_Name;
  }
  //endregion
}
