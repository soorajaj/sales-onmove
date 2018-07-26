package com.salestype.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.salestype.R;
import com.salestype.adapter.adaptervoucherdetails;
import com.salestype.model.Invoice;
import com.salestype.model.InvoiceItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceDetails extends AppCompatActivity {

    @BindView(R.id.txt_voucher_id)
    TextView txt_voucher_id;

    @BindView(R.id.txt_created_date)
    TextView txt_created_date;

    @BindView(R.id.txt_customer_name)
    TextView txt_customer_name;

    @BindView(R.id.txt_total_price)
    TextView txt_total_price;

    @BindView(R.id.tv_taxcharge)
    TextView tv_taxcharge;

    @BindView(R.id.txt_grandtotal)
    TextView txt_grandtotal;

    @BindView(R.id.list_voucher_items)
    RecyclerView list_voucher_items;

    ArrayList<Invoice> invoiceslist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details);
        ButterKnife.bind(this);

        list_voucher_items.setLayoutManager(new LinearLayoutManager(InvoiceDetails.this, LinearLayoutManager.VERTICAL, false));
        if (getIntent().hasExtra("invoicelist")) {
            invoiceslist=getIntent().getExtras().getParcelableArrayList("invoicelist");
        }
        if (invoiceslist!=null || invoiceslist.size()!=0){
            txt_voucher_id.setText(String.valueOf(invoiceslist.get(0).getInvoiceNo()));
            txt_created_date.setText(invoiceslist.get(0).getCreatedDate());
            txt_customer_name.setText(invoiceslist.get(0).getCustomerName());
            txt_grandtotal.setText(String.valueOf(invoiceslist.get(0).getGrandTotal()));
            Double total=invoiceslist.get(0).getGrandTotal()*100/105;
            txt_total_price.setText(String.valueOf(total));
            tv_taxcharge.setText(String.valueOf(invoiceslist.get(0).getGrandTotal()-total));
            populateData(invoiceslist.get(0).getInvoiceNo());
        }

    }

    public void populateData(int voucherid){
       List<InvoiceItem> invoiceItems=InvoiceItem.findWithQuery(InvoiceItem.class,"Select * from Invoice_item where Invoice_no = ?", String.valueOf(voucherid));
        adaptervoucherdetails adaptervoucherdetails=new adaptervoucherdetails(InvoiceDetails.this,invoiceItems);
        list_voucher_items.setAdapter(adaptervoucherdetails);
        Log.d("listitem",invoiceItems.get(0).ItemName);
    }
}
