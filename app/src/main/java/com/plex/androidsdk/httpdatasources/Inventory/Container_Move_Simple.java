/*
 * Copyright 2019 Plex Systems, Inc
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 *  associated documentation files (the "Software"), to deal in the Software without restriction,
 *  including without limitation the rights to use, copy, modify, merge, publish, distribute,
 *  sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or
 *  substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 *  BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.plex.androidsdk.httpdatasources.Inventory;

import com.plex.androidsdk.httpdatasources.BaseOutputs;
import com.plex.androidsdk.httpdatasources.DataSource;
import com.plex.androidsdk.httpdatasources.HttpDataSourceCredentials;
import com.plex.androidsdk.httpdatasources.IBaseInput;
import com.plex.androidsdk.httpdatasources.IDataSourceCallback;
import com.plex.androidsdk.httpdatasources.IDataSourceConnector;
import java.lang.reflect.Type;

public class Container_Move_Simple extends DataSource {

  private InputParameters inputParameters = new InputParameters();

  public Container_Move_Simple(
      IDataSourceCallback iDataSourceCallback,
      HttpDataSourceCredentials credentials,
      String serverName, boolean useTestServer) {
    super(iDataSourceCallback, credentials, serverName, useTestServer);
  }

  protected Container_Move_Simple(IDataSourceCallback iDataSourceCallback,
      HttpDataSourceCredentials credentials, String serverName, boolean useTestServer,
      IDataSourceConnector connector) {
    super(iDataSourceCallback, credentials, serverName, useTestServer, connector);
  }

  public void setLocation(String location) {
    inputParameters.Location = location;

  }

  public void setSerialNo(String serialNo) {
    inputParameters.Serial_No = serialNo;

  }

  public void setUpdateBy(int updateBy) {
    inputParameters.Update_By = updateBy;

  }

  public void setValidateLocation(boolean validateLocation) {
    inputParameters.ValidateLocation = validateLocation;

  }

  @Override
  protected int getDataSourceKey() {
    return 17218;
  }

  @Override
  protected IBaseInput getBaseInput() {
    return inputParameters;
  }

  @Override
  protected Type getOutputType() {
    return OutputParameters.class;
  }

  @Override
  protected Type getRowType() {
    return null;
  }

  protected class InputParameters implements IBaseInput {

    public String Location = "";
    public String Serial_No = "";
    public int Update_By = 0;
    public boolean ValidateLocation = false;

  }
  protected class OutputParameters extends BaseOutputs{
    public int RetVal;

  }
}
