
package com.salestype.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stock {

    @SerializedName("StockDetails")
    @Expose
    private List<StockDetail> stockDetails = null;

    @SerializedName("CustomerDetails")
    @Expose
    private List<CustomerDetails> CustomerDetails = null;

    public List<StockDetail> getStockDetails() {
        return stockDetails;
    }

    public void setStockDetails(List<StockDetail> stockDetails) {
        this.stockDetails = stockDetails;
    }

    public List<CustomerDetails> getCustomerDetails() {
        return CustomerDetails;
    }

    public void setCustomerDetails(List<CustomerDetails> customerDetails) {
        CustomerDetails = customerDetails;
    }
}
