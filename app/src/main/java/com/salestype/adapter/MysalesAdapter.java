package com.salestype.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.salestype.R;
import com.salestype.model.Invoice;

import java.util.List;

/**
 * Created by sooraj on 23/7/18.
 */
public class MysalesAdapter  extends RecyclerView.Adapter<MysalesAdapter.CustomViewHolder> {
    private Context mContext;

    private MysalesAdapter.CustomViewHolder mHolder;
    private List<Invoice> mysailslist;

    public MysalesAdapter(Context mContext, List<Invoice> mysailslist) {
        this.mContext = mContext;
        this.mysailslist = mysailslist;
    }

    @Override
    public MysalesAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        mHolder = new MysalesAdapter.CustomViewHolder(inflater.inflate(R.layout.adaptermysales, parent, false));
        return mHolder;
    }

    @Override
    public void onBindViewHolder(MysalesAdapter.CustomViewHolder holder, final int position) {
        final Invoice invoice = mysailslist.get(position);
        holder.textview_customername.setText(" "+invoice.getCustomerName());
        holder.textview_crdate.setText(" "+invoice.getCreatedDate());
        holder.textview_voucherid.setText(" "+String.valueOf(invoice.getInvoiceNo()));
        holder.textview_itemtotal.setText(" "+String.valueOf(invoice.getGrandTotal()));
    }

    @Override
    public int getItemCount() {
        return mysailslist.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        public TextView textview_customername,textview_voucherid,textview_crdate,textview_itemtotal;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.textview_customername = itemView.findViewById(R.id.textview_customername);
            this.textview_voucherid = itemView.findViewById(R.id.textview_voucherid);
            this.textview_crdate = itemView.findViewById(R.id.textview_crdate);
            this.textview_itemtotal = itemView.findViewById(R.id.textview_itemtotal);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;

        }
    }
}