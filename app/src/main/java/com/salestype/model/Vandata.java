package com.salestype.model;

/**
 * Created by sooraj on 10/7/18.
 */
public class Vandata {
    private int mVanId;
    private String mVanName;

    public Vandata(int mVanId, String mVanName) {
        this.mVanId = mVanId;
        this.mVanName = mVanName;
    }

    public int getmVanId() {
        return mVanId;
    }

    public void setmVanId(int mVanId) {
        this.mVanId = mVanId;
    }

    public String getmVanName() {
        return mVanName;
    }

    public void setmVanName(String mVanName) {
        this.mVanName = mVanName;
    }
}
