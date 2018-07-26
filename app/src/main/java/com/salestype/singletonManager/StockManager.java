package com.salestype.singletonManager;

import android.content.Context;

import com.salestype.model.StockDetail;

import java.util.ArrayList;

/**
 * Created by sooraj on 20/7/18.
 */
public class StockManager {
    private Context context;

    StockManager(Context context) {
        this.context = context.getApplicationContext();
    }

    private ArrayList<StockDetail> arrayList = new ArrayList<>();

    public ArrayList<StockDetail> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<StockDetail> arrayList) {
        this.arrayList = arrayList;
    }
    public void addOrderItem(int orderItempos, StockDetail stockDetail) {
        if (orderItempos > 0) {
            arrayList.add(orderItempos, stockDetail);
        } else {
                arrayList.add(stockDetail);

        }
    }
    public void updateQty(int pos,Double qty){
        arrayList.get(pos).setBalances(qty);
    }
    public void updatePrice(int pos,Double price){
        arrayList.get(pos).setSRate(price);
    }
    public void removeItem(int pos){
      arrayList.remove(pos);
    }
    public void cleanup() {
        arrayList.clear();
    }
}
