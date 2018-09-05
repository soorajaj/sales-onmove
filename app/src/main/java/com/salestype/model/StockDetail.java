
package com.salestype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class StockDetail extends SugarRecord {

    public StockDetail() {

    }

    public StockDetail(String branchid, Integer productID, String pCODE, String pNAME, String batchId, String batchCode, String barcode, Double balances, Integer unitId, String uNit, Double cF, Double sRate, Double mRP) {
        this.branchid = branchid;
        this.productID = productID;
        this.pCODE = pCODE;
        this.pNAME = pNAME;
        this.batchId = batchId;
        this.batchCode = batchCode;
        this.barcode = barcode;
        this.balances = balances;
        this.unitId = unitId;
        this.uNit = uNit;
        this.cF = cF;
        this.sRate = sRate;
        this.mRP = mRP;
    }

    public StockDetail(Integer productID, String pNAME, String batchCode, Double balances, Double sRate,Double orgbalance,Integer unitId,String batchId) {
        this.productID = productID;
        this.pNAME = pNAME;
        this.batchCode = batchCode;
        this.balances = balances;
        this.sRate = sRate;
        this.orgbalance=orgbalance;
        this.unitId=unitId;
        this.batchId=batchId;
    }

    @SerializedName("branchid")
    @Expose
    private String branchid;
    @SerializedName("productID")
    @Expose
    private Integer productID;
    @SerializedName("PCODE")
    @Expose
    private String pCODE;
    @SerializedName("PNAME")
    @Expose
    private String pNAME;
    @SerializedName("batchId")
    @Expose
    private String batchId;
    @SerializedName("batchCode")
    @Expose
    private String batchCode;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("Balances")
    @Expose
    private Double balances;
    @SerializedName("unitId")
    @Expose
    private Integer unitId;
    @SerializedName("UNit")
    @Expose
    private String uNit;
    @SerializedName("CF")
    @Expose
    private Double cF;
    @SerializedName("SRate")
    @Expose
    private Double sRate;
    @SerializedName("MRP")
    @Expose
    private Double mRP;

    private boolean isSelected;

    private Double selectedQty;
    private Double selectedamd;

    public Double getSelectedQty() {
        return selectedQty;
    }

    public void setSelectedQty(Double selectedQty) {
        this.selectedQty = selectedQty;
    }

    public Double getSelectedamd() {
        return selectedamd;
    }

    public void setSelectedamd(Double selectedamd) {
        this.selectedamd = selectedamd;
    }
    private Double orgbalance;

    public Double getOrgbalance() {
        return orgbalance;
    }

    public void setOrgbalance(Double orgbalance) {
        this.orgbalance = orgbalance;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getPCODE() {
        return pCODE;
    }

    public void setPCODE(String pCODE) {
        this.pCODE = pCODE;
    }

    public String getPNAME() {
        return pNAME;
    }

    public void setPNAME(String pNAME) {
        this.pNAME = pNAME;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Double getBalances() {
        return balances;
    }

    public void setBalances(Double balances) {
        this.balances = balances;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUNit() {
        return uNit;
    }

    public void setUNit(String uNit) {
        this.uNit = uNit;
    }

    public Double getCF() {
        return cF;
    }

    public void setCF(Double cF) {
        this.cF = cF;
    }

    public Double getSRate() {
        return sRate;
    }

    public void setSRate(Double sRate) {
        this.sRate = sRate;
    }

    public Double getMRP() {
        return mRP;
    }

    public void setMRP(Double mRP) {
        this.mRP = mRP;
    }

}
