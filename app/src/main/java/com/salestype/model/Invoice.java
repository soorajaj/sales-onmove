package com.salestype.model;

import com.orm.SugarRecord;

/**
 * Created by sooraj on 23/7/18.
 */
public class Invoice extends SugarRecord {
    public Invoice() {
    }
    public int InvoiceNo;
    public String CustomerName;
    public String CreatedDate;
    public Double GrandTotal;

    public Invoice(int invoiceNo, String customerName, String createdDate, Double grandTotal) {
        InvoiceNo = invoiceNo;
        CustomerName = customerName;
        CreatedDate = createdDate;
        GrandTotal = grandTotal;
    }

    public int getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public Double getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        GrandTotal = grandTotal;
    }
}
