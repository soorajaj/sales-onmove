package com.salestype.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.salestype.model.CustomerDetails;
import com.salestype.model.Login;
import com.salestype.model.Stock;
import com.salestype.R;
import com.salestype.model.StockDetail;
import com.salestype.network.ApiClient;
import com.salestype.network.ApiService;
import com.salestype.utilites.UtilsSharedPrefrence;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginView extends AppCompatActivity {

    @BindView(R.id.button_submit)
    Button mButton_submit;

    @BindView(R.id.edit_text_password)
    EditText mEditTextPassword;

    @BindView(R.id.edit_text_userName)
    EditText mEditTextUserName;

    private ApiService apiService;
    private Context mContext;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext=LoginView.this;
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);

        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (!mEditTextUserName.getText().toString().equalsIgnoreCase("")&&!mEditTextPassword.getText().toString().equalsIgnoreCase("")) {
                  login(mEditTextUserName.getText().toString(),mEditTextPassword.getText().toString());
              }else {
                  Toast.makeText(LoginView.this,"please fill the field to continue",Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
    public void login(String mUserName,String mPassword){
         pd = new ProgressDialog(LoginView.this);
         pd.setMessage("Please Wait...");
         pd.setCancelable(false);
        pd.show();
        apiService.Login(mUserName,mPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Login>() {
                    @Override
                    public void onSuccess(Login value) {
                        Log.d("mylogggg",String.valueOf(value.getSuccess()));
                        try {
                            if (value.getSuccess().equalsIgnoreCase("Successfully Logged In")){
                                UtilsSharedPrefrence.storeApiKey(LoginView.this,String.valueOf(1008));
                                getStock(String.valueOf(value.getVanDetails().get(0).getWarehouseID()),String.valueOf(1008));
                            }else {
                                pd.dismiss();
                                Toast.makeText(LoginView.this,"Login faild, place chaeck your username/password ",Toast.LENGTH_SHORT).show();

                            }
                        }catch (Exception e){
                            pd.dismiss();
                            Toast.makeText(LoginView.this,"Login faild, place chaeck your username/password ",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();
                        pd.dismiss();
                    }
                });
    }
    public void getStock(String vanid,String branchid){
        pd.setMessage("Loading Stock Details");
        apiService.GetStock(vanid,branchid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Stock>() {
                    @Override
                    public void onSuccess(Stock stock) {
                        try {
                            StockDetail.deleteAll(StockDetail.class);
                            CustomerDetails.deleteAll(CustomerDetails.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                            pd.dismiss();
                        }
                        if (stock.getStockDetails().size()!=0) {

                            for (int i = 0; i < stock.getStockDetails().size(); i++) {
                                StockDetail stockDetail1 = stock.getStockDetails().get(i);
                                StockDetail stockDetail = new StockDetail(stockDetail1.getBranchid(), stockDetail1.getProductID(), stockDetail1.getPCODE(), stockDetail1.getPNAME(), stockDetail1.getBatchId(),
                                        stockDetail1.getBatchCode(), stockDetail1.getBarcode(), stockDetail1.getBalances(), stockDetail1.getUnitId(),
                                        stockDetail1.getUNit(), stockDetail1.getCF(), stockDetail1.getSRate(), stockDetail1.getMRP());
                                stockDetail.save();

                            }
                            for (int i = 0; i < stock.getCustomerDetails().size(); i++) {
                                CustomerDetails customerDetails = stock.getCustomerDetails().get(i);
                                CustomerDetails  customerDetails1= new CustomerDetails(customerDetails.getLedgerID(),customerDetails.getLedgerName(),customerDetails.getBalance(),customerDetails.getAddress(),customerDetails.getCellPhone(),customerDetails.getCreditAmt());
                                customerDetails1.save();

                            }

                            pd.dismiss();
                            Intent intent=new Intent(LoginView.this,LandingPage.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                       e.printStackTrace();
                        pd.dismiss();
                    }
                });

    }
}
