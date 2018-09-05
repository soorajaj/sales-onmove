package com.salestype.model;

import com.orm.SugarRecord;

/**
 * Created by sooraj on 23/7/18.
 */
public class InvoiceItem extends SugarRecord {
    public InvoiceItem() {
    }

    public String ItemName;
    public Double ItemQuantity;
    public int InvoiceNo;
    public Double ItemPrice;
    private Integer ItemProductID;
    private Integer ItemUnitId;
    private String ItemBatchId;


    public InvoiceItem(String itemName, Double itemQuantity, int invoiceNo,Double itemPrice,int ItemProductID,int ItemUnitId,String batchId) {
        ItemName = itemName;
        ItemQuantity = itemQuantity;
        InvoiceNo = invoiceNo;
        ItemPrice=itemPrice;
        ItemProductID=ItemProductID;
        ItemUnitId=ItemUnitId;
        batchId=batchId;
    }

    public Integer getItemProductID() {
        return ItemProductID;
    }

    public void setItemProductID(Integer itemProductID) {
        ItemProductID = itemProductID;
    }

    public Integer getItemUnitId() {
        return ItemUnitId;
    }

    public void setItemUnitId(Integer itemUnitId) {
        ItemUnitId = itemUnitId;
    }

    public Double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public Double getItemQuantity() {
        return ItemQuantity;
    }

    public void setItemQuantity(Double itemQuantity) {
        ItemQuantity = itemQuantity;
    }

    public int getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        InvoiceNo = invoiceNo;
    }
}
