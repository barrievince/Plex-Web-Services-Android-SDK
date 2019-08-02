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

package com.plex.androidsdk.httpdatasources.Inventory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.math.BigDecimal;
import org.junit.Test;

public class Container_Get1Test {

  /**
   * Test the getDataSourceKey method
   */
  @Test
  public void getDataSourceKey() {
    int expected = 6455;
    int output = new Container_Get1(null, null, null).getDataSourceKey();
    assertEquals(expected, output);
  }

  /**
   * Test setting and getting serial no.
   */
  @Test
  public void testSerialNo() {
    String expectedValue = "S123456";

    Container_Get1 cg1 = new Container_Get1(null, null, null);
    cg1.setSerialNo(expectedValue);

    assertEquals(expectedValue, cg1.getSerialNo());
  }

  /**
   * Test JSON input parameters format
   */
  @Test
  public void testInputParameterJSON() {
    String serialNo = "S123456";
    String expectedValue = "{\"Serial_No\":\"S123456\"}";

    Container_Get1 cgl = new Container_Get1(null, null, null);
    cgl.setSerialNo(serialNo);
    assertEquals(expectedValue, cgl.getJsonRequest());
  }


  /**
   * Test parsing a row.
   */
  @Test
  public void parseRow_Good() {
    ExpectedGoodRow expectedData = new ExpectedGoodRow();
    JsonObject inputData = this.getGoodRowJsonArray();

    Container_Get1.Row row = (Container_Get1.Row) new Container_Get1(null, null, null).parseRow(inputData);

    assertNotNull(row);
    assertEquals(expectedData.PartNoRevision, row.getPartNoRevision());
    assertEquals(expectedData.Name, row.getName());
    assertEquals(expectedData.PartKey, row.getPartKey());
    assertEquals(expectedData.OperationCode, row.getOperationCode());
    assertEquals(expectedData.Quantity, row.getQuantity());
    assertEquals(expectedData.ContainerStatus, row.getContainerStatus());
    assertEquals(expectedData.Location, row.getLocation());
    assertEquals(expectedData.Note, row.getNote());
    assertEquals(expectedData.OperationKey, row.getOperationKey());
    assertEquals(expectedData.ReworkOperation, row.getReworkOperation());
    assertEquals(expectedData.SpecialInstructions, row.getSpecialInstructions());
    assertEquals(expectedData.DefectType, row.getDefectType());

  }

  /**
   * Test parsing a row containing a null for OperationKey.
   */
  @Test
  public void parseRow_WithNull() {
    ExpectedGoodRowWithNulls expectedData = new ExpectedGoodRowWithNulls();
    JsonObject inputData = this.getGoodRowWithNullJsonArray();

    Container_Get1.Row row = (Container_Get1.Row) new Container_Get1(null, null, null).parseRow(inputData);

    assertNotNull(row);
    assertEquals(expectedData.PartNoRevision, row.getPartNoRevision());
    assertEquals(expectedData.Name, row.getName());
    assertEquals(expectedData.PartKey, row.getPartKey());
    assertEquals(expectedData.OperationCode, row.getOperationCode());
    assertEquals(expectedData.Quantity, row.getQuantity());
    assertEquals(expectedData.ContainerStatus, row.getContainerStatus());
    assertEquals(expectedData.Location, row.getLocation());
    assertEquals(expectedData.Note, row.getNote());
    assertEquals(expectedData.OperationKey, row.getOperationKey());
    assertEquals(expectedData.ReworkOperation, row.getReworkOperation());
    assertEquals(expectedData.SpecialInstructions, row.getSpecialInstructions());
    assertEquals(expectedData.DefectType, row.getDefectType());

  }

  /**
   * Returns a valid, non-null, JsonObject of a row.
   *
   * @return JsonObject A row of data.
   */
  private JsonObject getGoodRowJsonArray() {
    String someObject = "{\n"
        + "            \"Part_No_Revision\": \"Part No Revision Value\",\n"
        + "            \"Name\": \"Part Name Value\",\n"
        + "            \"Part_Key\": 99999,\n"
        + "            \"Operation_Code\": \"Operation Code Value\",\n"
        + "            \"Quantity\": 99999.0000000000000000000,\n"
        + "            \"Container_Status\": \"Container Status Value\",\n"
        + "            \"Location\": \"Location Value\",\n"
        + "            \"Note\": \"Note Value\",\n"
        + "            \"Operation_Key\": 99999,\n"
        + "            \"Rework_Operation\": 99999,\n"
        + "            \"Special_Instructions\": \"Special Instructions Value\",\n"
        + "            \"Defect_Type\": \"Defect Type Value\"\n"
        + "        }";

    return new JsonParser().parse(someObject).getAsJsonObject();
  }

  /**
   * Returns a valid, non-null, JsonObject of a row.
   *
   * @return JsonObject A row of data.
   */
  private JsonObject getGoodRowWithNullJsonArray() {
    String someObject = "{\n"
        + "            \"Part_No_Revision\": \"Part No Revision Value\",\n"
        + "            \"Name\": \"Part Name Value\",\n"
        + "            \"Part_Key\": 99999,\n"
        + "            \"Operation_Code\": \"Operation Code Value\",\n"
        + "            \"Quantity\": 99999.0000000000000000000,\n"
        + "            \"Container_Status\": \"Container Status Value\",\n"
        + "            \"Location\": \"Location Value\",\n"
        + "            \"Note\": \"Note Value\",\n"
        + "            \"Operation_Key\": 99999,\n"
        + "            \"Rework_Operation\": null,\n"
        + "            \"Special_Instructions\": \"Special Instructions Value\",\n"
        + "            \"Defect_Type\": \"Defect Type Value\"\n"
        + "        }";

    return new JsonParser().parse(someObject).getAsJsonObject();
  }


  /**
   * A class containing the expected data values for getGoodRowJsonArray.
   */
  private class ExpectedGoodRow {

    String PartNoRevision = "Part No Revision Value";
    String Name = "Part Name Value";
    int PartKey = 99999;
    String OperationCode = "Operation Code Value";
    BigDecimal Quantity = new BigDecimal("99999.0000000000000000000");
    String ContainerStatus = "Container Status Value";
    String Location = "Location Value";
    String Note = "Note Value";
    int OperationKey = 99999;
    int ReworkOperation = 99999;
    String SpecialInstructions = "Special Instructions Value";
    String DefectType = "Defect Type Value";
  }

  /**
   * A class containing the expected data values for getGoodRowJsonArray.
   */
  private class ExpectedGoodRowWithNulls {

    String PartNoRevision = "Part No Revision Value";
    String Name = "Part Name Value";
    int PartKey = 99999;
    String OperationCode = "Operation Code Value";
    BigDecimal Quantity = new BigDecimal("99999.0000000000000000000");
    String ContainerStatus = "Container Status Value";
    String Location = "Location Value";
    String Note = "Note Value";
    int OperationKey = 99999;
    int ReworkOperation = 0;
    String SpecialInstructions = "Special Instructions Value";
    String DefectType = "Defect Type Value";
  }
}