
package com.salestype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VanDetail {

    @SerializedName("warehouseID")
    @Expose
    private Integer warehouseID;
    @SerializedName("warehouseCode")
    @Expose
    private String warehouseCode;
    @SerializedName("warehouseName")
    @Expose
    private String warehouseName;
    @SerializedName("empID1")
    @Expose
    private String empID1;

    public String getEmpID1() {
        return empID1;
    }

    public void setEmpID1(String empID1) {
        this.empID1 = empID1;
    }

    public Integer getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }


}
