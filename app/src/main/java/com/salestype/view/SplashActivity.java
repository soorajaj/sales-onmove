package com.salestype.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.salestype.R;
import com.salestype.listener.AlertListener;
import com.salestype.utilites.UtilsSharedPrefrence;
import com.salestype.utilites.utility;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                if(utility.isConnectingToInternet(SplashActivity.this)){

                    if (TextUtils.isEmpty(UtilsSharedPrefrence.getApiKey(SplashActivity.this))) {
                        Intent intent=new Intent(SplashActivity.this,LoginView.class);
                        startActivity(intent);
                        finish();

                    } else {
                        // user is already registered, fetch all notes
                        Intent intent=new Intent(SplashActivity.this,LandingPage.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    utility.showInformationAlert(SplashActivity.this, getString(R.string.no_internet_connection), new AlertListener() {
                        @Override
                        public void onSubmit() {
                            finish();
                        }

                        @Override
                        public void onCancel() {
                            finish();
                        }
                    });
                }


            }
        }, SPLASH_TIME_OUT);

    }
}
