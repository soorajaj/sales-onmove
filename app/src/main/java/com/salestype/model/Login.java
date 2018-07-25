
package com.salestype.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("VanDetails")
    @Expose
    private List<VanDetail> vanDetails = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<VanDetail> getVanDetails() {
        return vanDetails;
    }

    public void setVanDetails(List<VanDetail> vanDetails) {
        this.vanDetails = vanDetails;
    }

}
