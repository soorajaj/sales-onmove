package com.salestype.singletonManager;


import android.content.Context;

public class ObjectFactory {

    private static ObjectFactory instance = null;


    private StockManager stockmanager = null;

    private ObjectFactory() {
    }

    public synchronized static ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }
        return instance;
    }

    public StockManager getStockmanager(Context context) {
        if (stockmanager == null) {
            stockmanager = new StockManager(context);
        }
        return stockmanager;
    }


}