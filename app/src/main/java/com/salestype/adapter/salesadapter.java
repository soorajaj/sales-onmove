package com.salestype.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.salestype.R;
import com.salestype.listener.Calculatetotal;
import com.salestype.listener.DeleteItem;
import com.salestype.listener.Updatelis;
import com.salestype.model.StockDetail;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by sooraj on 20/7/18.
 */
public class salesadapter extends RecyclerView.Adapter<salesadapter.CustomViewHolder> {
    private Context mContext;

    private salesadapter.CustomViewHolder mHolder;
    private List<StockDetail> mStockListArrayList;
    private DeleteItem deleteItemlistener;
    private Updatelis updatelistener;
    private Calculatetotal calculatetotal;

    public salesadapter(Context mContext, List<StockDetail> addressResults, DeleteItem deleteItemlistener, Updatelis updatelistener, Calculatetotal calculatetotal) {
        this.mContext = mContext;
        this.mStockListArrayList = addressResults;
        this.deleteItemlistener=deleteItemlistener;
        this.updatelistener=updatelistener;
        this.calculatetotal=calculatetotal;
        calculat(mStockListArrayList);
    }

    @Override
    public salesadapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        mHolder = new salesadapter.CustomViewHolder(inflater.inflate(R.layout.adapter_sails, parent, false));
        return mHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        final StockDetail stock=mStockListArrayList.get(position);
        holder.tv_item_name.setText(stock.getPNAME());
        DecimalFormat df = new DecimalFormat("#.###");
        Double total= stock.getBalances()*stock.getSRate();
        holder.tv_unit.setText(String.valueOf(stock.getSRate())+"*"+String.valueOf(stock.getBalances())+"="+df.format(total));
//        holder.tv_price.setText("Cost: "+String.valueOf(total));
//        holder.tv_Itemprice.setText("Unit Prize: "+String.valueOf(stock.getSRate()));
        holder.layout_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatelistener.mUpdateList(position,stock);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItemlistener.mDeleteItem(position,stock);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatelistener.mUpdateList(position,stock);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStockListArrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        public TextView tv_item_name,tv_unit,tv_price,edit,delete,tv_Itemprice;
        public FrameLayout layout_action;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.tv_item_name = itemView.findViewById(R.id.tv_item_name);
            this.tv_unit = itemView.findViewById(R.id.tv_unit);
//            this.tv_price = itemView.findViewById(R.id.tv_price);
            this.edit = itemView.findViewById(R.id.edit);
            this.delete = itemView.findViewById(R.id.delete);
            this.layout_action = itemView.findViewById(R.id.layout_action);
//            this.tv_Itemprice = itemView.findViewById(R.id.tv_Itemprice);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;

        }
    }
    public void calculat(List<StockDetail> mStockListArrayList){
        Double total=0.0;
        for (StockDetail stockDetail:mStockListArrayList) {
            total=total+stockDetail.getBalances()*stockDetail.getSRate();
        }
        calculatetotal.calculateTotal(total);
    }
}