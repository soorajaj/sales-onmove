package com.salestype.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salestype.R;
import com.salestype.listener.GetInvoice;
import com.salestype.model.Invoice;
import com.salestype.model.InvoiceItem;

import java.util.List;

/**
 * Created by sooraj on 26/7/18.
 */
public class adaptervoucherdetails extends RecyclerView.Adapter<adaptervoucherdetails.CustomViewHolder> {
    private Context mContext;

    private adaptervoucherdetails.CustomViewHolder mHolder;
    private List<InvoiceItem> mysailslist;
    public adaptervoucherdetails(Context mContext, List<InvoiceItem> mysailslist) {
        this.mContext = mContext;
        this.mysailslist = mysailslist;
    }

    @Override
    public adaptervoucherdetails.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        mHolder = new adaptervoucherdetails.CustomViewHolder(inflater.inflate(R.layout.adapter_invoicesummery, parent, false));
        return mHolder;
    }

    @Override
    public void onBindViewHolder(adaptervoucherdetails.CustomViewHolder holder, final int position) {
        final InvoiceItem invoice = mysailslist.get(position);
        holder.txt_itemname_qty.setText(invoice.getItemName()+" (Qty:"+String.valueOf(invoice.getItemQuantity())+")");
        holder.txt_item_price.setText(String.valueOf(invoice.getItemPrice()));
    }

    @Override
    public int getItemCount() {
        return mysailslist.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        public TextView txt_itemname_qty,txt_item_price;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.txt_itemname_qty = itemView.findViewById(R.id.txt_itemname_qty);
            this.txt_item_price = itemView.findViewById(R.id.txt_item_price);
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