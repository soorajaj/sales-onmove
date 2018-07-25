package com.salestype.utilites;

import android.content.Context;
import android.content.SharedPreferences;

import com.salestype.model.Invoice;
import com.salestype.model.InvoiceItem;
import com.salestype.model.StockDetail;

/**
 * Created by sooraj on 19/7/18.
 */
public class UtilsSharedPrefrence {

    public UtilsSharedPrefrence() {
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("APP_PREF", Context.MODE_PRIVATE);
    }

    public static void storeApiKey(Context context, String apiKey) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("API_KEY", apiKey);
        editor.commit();
    }

    public static String getApiKey(Context context) {
        return getSharedPreferences(context).getString("API_KEY", null);
    }

    public static void storeInvoiceNo(Context context, int Invoiceno) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt("INVOICE_NO", Invoiceno);
        editor.commit();
    }

    public static int getinvoiceNo(Context context) {
        return getSharedPreferences(context).getInt("INVOICE_NO", 1);
    }
    public static void clearPreference(Context context) {
        try {
            SharedPreferences.Editor editor = getSharedPreferences(context).edit();
            editor.clear();
            editor.apply();
            StockDetail.deleteAll(StockDetail.class);
            Invoice.deleteAll(Invoice.class);
            InvoiceItem.deleteAll(InvoiceItem.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
