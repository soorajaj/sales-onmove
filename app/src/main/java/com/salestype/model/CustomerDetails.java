package com.salestype.model;

import com.orm.SugarRecord;

/**
 * Created by sooraj on 2/8/18.
 */
public class CustomerDetails extends SugarRecord {
    private String ledgerID;
    private String ledgerName;
    private Double balance;
    private String Address;
    private String cellPhone;
    private Double CreditAmt;

    public CustomerDetails() {
    }

    public CustomerDetails(String ledgerID, String ledgerName, Double balance, String address, String cellPhone, Double creditAmt) {
        this.ledgerID = ledgerID;
        this.ledgerName = ledgerName;
        this.balance = balance;
        Address = address;
        this.cellPhone = cellPhone;
        CreditAmt = creditAmt;
    }

    public String getLedgerID() {
        return ledgerID;
    }

    public void setLedgerID(String ledgerID) {
        this.ledgerID = ledgerID;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Double getCreditAmt() {
        return CreditAmt;
    }

    public void setCreditAmt(Double creditAmt) {
        CreditAmt = creditAmt;
    }
}
