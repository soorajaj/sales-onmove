package com.salestype.view.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarRecord;
import com.salestype.R;
import com.salestype.adapter.VanSpinnerAdapter;
import com.salestype.adapter.salesadapter;
import com.salestype.listener.AlertListener;
import com.salestype.listener.Calculatetotal;
import com.salestype.listener.DeleteItem;
import com.salestype.listener.Updatelis;
import com.salestype.model.CustomerDetails;
import com.salestype.model.Invoice;
import com.salestype.model.InvoiceItem;
import com.salestype.model.StockDetail;
import com.salestype.singletonManager.ObjectFactory;
import com.salestype.utilites.ItemClickSupport;
import com.salestype.utilites.UtilsSharedPrefrence;
import com.salestype.utilites.utility;
import com.salestype.view.LandingPage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CreateSales extends Fragment {

    @BindView(R.id.stock_item_list)
    RecyclerView mRecyclerStockList;

    @BindView(R.id.linearlayout_Save)
    LinearLayout linearlayout_Save;

    @BindView(R.id.linearlayout_addmore)
    LinearLayout linearlayout_addmore;

    @BindView(R.id.txt_item_netprice)
    TextView txt_item_netprice;

    @BindView(R.id.txt_totalamd)
    TextView txt_totalamd;

    @BindView(R.id.txt_item_tax)
    TextView txt_item_tax;

    @BindView(R.id.text_companyname)
    TextView text_companyname;

    @BindView(R.id.txt_item_netbalance)
    TextView txt_item_netbalance;

    @BindView(R.id.txt_item_balance)
    TextView txt_item_balance;

    @BindView(R.id.text_invoiceno)
    TextView text_invoiceno;

    @BindView(R.id.text_date)
    TextView text_date;

//    @BindView(R.id.edit_username)
//    EditText edit_username;


    @BindView(R.id.spinner_customername)
    Spinner spinner_customername;

    salesadapter stockListAdapter;
    ArrayList<StockDetail> mStockListArrayList = new ArrayList<>();
    private StockList.OnItemStartActivityListener listener;
    private List<CustomerDetails> customerDetails;
    CustomerDetails selected_customerDetails;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_sails, container, false);
        ButterKnife.bind(this, view);
        try {
            customerDetails=CustomerDetails.listAll(CustomerDetails.class);
            VanSpinnerAdapter vanSpinnerAdapter=new VanSpinnerAdapter(getActivity(),R.layout.adapter_vanspinner,R.id.title,customerDetails);
            spinner_customername.setAdapter(vanSpinnerAdapter);
            spinner_customername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selected_customerDetails=customerDetails.get(i);
                    txt_item_netbalance.setText(String.valueOf(selected_customerDetails.getBalance()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            text_invoiceno.setText("Invoice No: "+UtilsSharedPrefrence.getinvoiceNo(getContext()));
            mStockListArrayList = ObjectFactory.getInstance().getStockmanager(getContext()).getArrayList();
            if (mStockListArrayList != null && mStockListArrayList.size() != 0) {
                stockListAdapter = new salesadapter(this.getActivity(), mStockListArrayList, new DeleteItem() {
                    @Override
                    public void mDeleteItem(int pos, StockDetail stockDetail) {
                        ObjectFactory.getInstance().getStockmanager(getActivity()).removeItem(pos);
                        stockListAdapter.calculat( ObjectFactory.getInstance().getStockmanager(getActivity()).getArrayList());
                        stockListAdapter.notifyDataSetChanged();
                    }
                }, new Updatelis() {
                    @Override
                    public void mUpdateList(int pos, StockDetail stockDetail) {
                        showInformationAlert(stockDetail, getActivity(), pos,stockDetail.getOrgbalance());
                    }
                }, new Calculatetotal() {
                    @Override
                    public void calculateTotal(Double value) {
                        DecimalFormat df = new DecimalFormat("#.###");
                       txt_totalamd.setText(df.format(value));
                       double tax=value*5/100;
                       txt_item_tax.setText(df.format(tax));
                       txt_item_netprice.setText(df.format(value+tax));
                    }
                });
                mRecyclerStockList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                mRecyclerStockList.setAdapter(stockListAdapter);
                ItemClickSupport.addTo(mRecyclerStockList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        if (position >= 0) {
                            showInformationAlert(mStockListArrayList.get(position), getActivity(), position,mStockListArrayList.get(position).getOrgbalance());
                        }
                    }
                });
            }
            linearlayout_addmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.Loaddata(3);
                }
            });
            text_date.setText(utility.mGetCurrentDateTime());

            linearlayout_Save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ObjectFactory.getInstance().getStockmanager(getContext()).getArrayList().size()!=0){
//                        if (!edit_username.getText().toString().equalsIgnoreCase("")){
                            Invoice invoice=new Invoice(UtilsSharedPrefrence.getinvoiceNo(getContext()),selected_customerDetails.getLedgerName(),text_date.getText().toString().trim(),Double.parseDouble(txt_item_netprice.getText().toString()));
                            invoice.save();
                            mStockListArrayList=ObjectFactory.getInstance().getStockmanager(getContext()).getArrayList();
                            for (StockDetail mStockDetail:mStockListArrayList) {
                                InvoiceItem invoiceItem=new InvoiceItem(mStockDetail.getPNAME(),mStockDetail.getBalances(),UtilsSharedPrefrence.getinvoiceNo(getContext()),mStockDetail.getSRate());
                                invoiceItem.save();

                            }
                            Toast.makeText(getActivity(),"invoice saved",Toast.LENGTH_SHORT).show();
                            ObjectFactory.getInstance().getStockmanager(getActivity()).cleanup();

                            UtilsSharedPrefrence.storeInvoiceNo(getActivity(),UtilsSharedPrefrence.getinvoiceNo(getActivity())+1);
                            listener.Loaddata(2);
//                        }
//                        else {
//                            Toast.makeText(getActivity(),"Customer name can't be empty",Toast.LENGTH_SHORT).show();
//                        }
                    }else {
                        utility.showInformationAlert(getActivity(), "No item to save, Invoice is Empty", new AlertListener() {
                            @Override
                            public void onSubmit() {

                            }

                            @Override
                            public void onCancel() {

                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }


    public void showInformationAlert(final StockDetail stockDetail, final Activity activity, final int position, final double originalstock) {
        try {
            final Dialog dialog = new Dialog(activity, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);
            dialog.setContentView(R.layout.dialog_collectdata);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView text_dilog_message = dialog.findViewById(R.id.text_dilog_message);
            TextView textview_itemQty = dialog.findViewById(R.id.textview_qty);
            TextView textview_itemprice = dialog.findViewById(R.id.text_mrp);
            TextView button_cancel = dialog.findViewById(R.id.button_cancel);
            final EditText edittextitem_quandity = dialog.findViewById(R.id.edittextitem_quandity);
            final EditText edittextitem_price = dialog.findViewById(R.id.edittextitem_price);
            TextView button_ok = dialog.findViewById(R.id.button_ok);
            text_dilog_message.setText(stockDetail.getPNAME());
            edittextitem_price.setText(String.valueOf(stockDetail.getSRate()));
            textview_itemQty.setText("Added Qty: " + stockDetail.getBalances());
            textview_itemprice.setText("MRP: " + stockDetail.getSRate());
            button_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!edittextitem_quandity.getText().toString().equalsIgnoreCase("")){
                        if (Double.parseDouble(edittextitem_quandity.getText().toString())<=originalstock){
                            ObjectFactory.getInstance().getStockmanager(getActivity()).updateQty(position,Double.parseDouble(edittextitem_quandity.getText().toString()));
                            ObjectFactory.getInstance().getStockmanager(getActivity()).updatePrice(position,Double.parseDouble(edittextitem_price.getText().toString()));

                            stockListAdapter.notifyDataSetChanged();
                            stockListAdapter.calculat( ObjectFactory.getInstance().getStockmanager(getActivity()).getArrayList());
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getActivity(), "Only " + String.valueOf(originalstock) + " item available", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(),"please fill the Quantity field",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            button_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//      ObjectFactory.getInstance().getStockmanager(getContext()).cleanup();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof StockList.OnItemStartActivityListener) {
//            Log.d("Annv - Fragment", "activity " + context.getLocalClassName());
            listener = (StockList.OnItemStartActivityListener) context;
        }
    }
}
