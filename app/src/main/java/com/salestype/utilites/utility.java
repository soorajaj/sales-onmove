package com.salestype.utilites;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.TextView;

import com.salestype.R;
import com.salestype.listener.AlertListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by sooraj on 19/7/18.
 */
public class utility {
    public static boolean isConnectingToInternet(Context mContext) {
        ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }
    public static void showInformationAlert(final Activity activity, String message, final AlertListener listener) {
        try {
            final Dialog dialog = new Dialog(activity, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);
            dialog.setContentView(R.layout.dialog_info);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView text_dilog_message = dialog.findViewById(R.id.text_dilog_message);
            text_dilog_message.setText(message);
//            TextView txt_hoppontxt = dialog.findViewById(R.id.txt_hoppontxt);
            TextView button_ok = dialog.findViewById(R.id.button_ok);
            button_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onSubmit();
                    dialog.dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showInformationAlertToButton(final Activity activity, String message, final AlertListener listener) {
        try {
            final Dialog dialog = new Dialog(activity, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);
            dialog.setContentView(R.layout.dialog_exit);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView text_dilog_message = dialog.findViewById(R.id.text_dilog_message);
            text_dilog_message.setText(message);
//            TextView txt_hoppontxt = dialog.findViewById(R.id.txt_hoppontxt);
            TextView button_ok = dialog.findViewById(R.id.button_ok);
            button_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onSubmit();
                    dialog.dismiss();
                }
            });
            TextView button_cancel = dialog.findViewById(R.id.button_cancel);
            button_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCancel();
                    dialog.dismiss();
                }
            });

            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String mGetCurrentDateTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

}
