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

    public InvoiceItem(String itemName, Double itemQuantity, int invoiceNo) {
        ItemName = itemName;
        ItemQuantity = itemQuantity;
        InvoiceNo = invoiceNo;
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
