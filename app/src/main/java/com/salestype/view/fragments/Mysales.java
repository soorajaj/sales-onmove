package com.salestype.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salestype.R;
import com.salestype.adapter.MysalesAdapter;
import com.salestype.listener.GetInvoice;
import com.salestype.model.Invoice;
import com.salestype.view.InvoiceDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Mysales extends Fragment {

    @BindView(R.id.recyclerview_mystocklit)
    RecyclerView recyclerview_mystocklit;

    List<Invoice> invoiceList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mysales, container, false);
        ButterKnife.bind(this, view);
        try {
            recyclerview_mystocklit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            invoiceList = Invoice.listAll(Invoice.class);
            MysalesAdapter mysalesAdapter = new MysalesAdapter(getActivity(), invoiceList, new GetInvoice() {
                @Override
                public void getInvoice(Invoice invoice) {
                    ArrayList<Invoice> invoicelist = new ArrayList<Invoice>();
                    invoicelist.add(invoice);
                    Intent intent=new Intent(getActivity(), InvoiceDetails.class);
                    intent.putParcelableArrayListExtra("invoicelist",invoicelist);
                    startActivity(intent);
                }
            });
            recyclerview_mystocklit.setAdapter(mysalesAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
    private void LoadData(int invoiceid){

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
    private void mCreatDataSet(){
        Jso
    }
}
