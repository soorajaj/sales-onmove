package com.salestype.adapter;

import android.content.Context;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.salestype.R;
import com.salestype.listener.Updatelis;
import com.salestype.model.StockDetail;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sooraj on 17/7/18.
 */
public class StockListAdapter extends RecyclerView.Adapter<StockListAdapter.CustomViewHolder> implements Filterable {
    private Context mContext;

    private CustomViewHolder mHolder;
    private List<StockDetail> mStockListArrayList;
    private List<StockDetail> mStockListArrayListfilter;
    private Updatelis updatelislistioner;

    public StockListAdapter(Context mContext, List<StockDetail> addressResults,Updatelis updatelislistioner) {
        this.mContext = mContext;
        this.mStockListArrayList = addressResults;
        this.mStockListArrayListfilter=addressResults;
        this.updatelislistioner=updatelislistioner;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        mHolder = new CustomViewHolder(inflater.inflate(R.layout.adapter_stock_list, parent, false));
        return mHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        final StockDetail stock=mStockListArrayListfilter.get(position);
        holder.mItemNameTextView.setText(stock.getPNAME());
        holder.mItemQtytextview.setText("Av. Qty:"+stock.getBalances()+" "+stock.getUNit());
        holder.mItemPrice.setText("MRP: "+String.valueOf(stock.getMRP()));
        if (mStockListArrayList.get(position).isSelected()){
            holder.linear_layout.setBackgroundColor(mContext.getResources().getColor(R.color.green_teal_new));
            holder.textview_sqty.setText("S.Qty:"+mStockListArrayList.get(position).getSelectedQty());
            DecimalFormat df = new DecimalFormat("#.###");
            holder.textview_sprize.setText("Amt: "+df.format(mStockListArrayList.get(position).getSelectedQty()*stock.getSRate()));
            holder.layout_sel.setVisibility(View.VISIBLE);

        }else {
            holder.linear_layout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        holder.click_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatelislistioner.mUpdateList(position,stock);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStockListArrayListfilter.size();

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mStockListArrayListfilter = mStockListArrayList;
                } else {
                    List<StockDetail> filteredList = new ArrayList<>();
                    for (StockDetail row : mStockListArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getPNAME().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    mStockListArrayListfilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mStockListArrayListfilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mStockListArrayListfilter = (ArrayList<StockDetail>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        public TextView  mItemNameTextView,mItemQtytextview,mItemPrice,textview_sqty,textview_sprize;
        public LinearLayout linear_layout,layout_sel;
        public CardView click_card;
        public CustomViewHolder(View itemView) {
            super(itemView);
            this.mItemNameTextView = itemView.findViewById(R.id.textview_itemname);
            this.mItemQtytextview = itemView.findViewById(R.id.textview_itemQty);
            this.mItemPrice = itemView.findViewById(R.id.textview_mrp);
            this.linear_layout = itemView.findViewById(R.id.linear_layout);
            this.click_card=itemView.findViewById(R.id.click_card);
            this.textview_sqty=itemView.findViewById(R.id.textview_sqty);
            this.textview_sprize=itemView.findViewById(R.id.textview_sprize);
            this.layout_sel=itemView.findViewById(R.id.layout_sel);
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
