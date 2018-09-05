package com.salestype.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by sooraj on 23/7/18.
 */
public class Invoice extends SugarRecord implements Parcelable {
    public Invoice() {
    }
    public int InvoiceNo;
    public String CustomerName;
    public String CreatedDate;
    public Double GrandTotal;
    public Double paidTotal;

    public Invoice(int invoiceNo, String customerName, String createdDate, Double grandTotal,Double paidTotal) {
        InvoiceNo = invoiceNo;
        CustomerName = customerName;
        CreatedDate = createdDate;
        GrandTotal = grandTotal;
        paidTotal=paidTotal;
    }

    public Double getPaidTotal() {
        return paidTotal;
    }

    public void setPaidTotal(Double paidTotal) {
        this.paidTotal = paidTotal;
    }

    protected Invoice(Parcel in) {
        InvoiceNo = in.readInt();
        CustomerName = in.readString();
        CreatedDate = in.readString();
        if (in.readByte() == 0) {
            GrandTotal = null;
        } else {
            GrandTotal = in.readDouble();
        }
        if (in.readByte() == 0) {
            paidTotal = null;
        } else {
            paidTotal = in.readDouble();
        }
    }

    public static final Creator<Invoice> CREATOR = new Creator<Invoice>() {
        @Override
        public Invoice createFromParcel(Parcel in) {
            return new Invoice(in);
        }

        @Override
        public Invoice[] newArray(int size) {
            return new Invoice[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(InvoiceNo);
        parcel.writeString(CustomerName);
        parcel.writeString(CreatedDate);
        if (GrandTotal == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(GrandTotal);
        }
        if (paidTotal == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(paidTotal);
        }
    }
}
