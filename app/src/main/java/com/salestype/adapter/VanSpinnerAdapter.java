package com.salestype.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.salestype.R;
import com.salestype.model.CustomerDetails;
import com.salestype.model.Vandata;

import java.util.List;

/**
 * Created by sooraj on 10/7/18.
 */
public class VanSpinnerAdapter extends ArrayAdapter<CustomerDetails> {
    LayoutInflater flater;

    public VanSpinnerAdapter(Activity context, int resouceId, int textviewId, List<CustomerDetails> list){

        super(context,resouceId,textviewId, list);
        flater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return rowview(convertView,position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView,position);
    }

    private View rowview(View convertView , int position){

        CustomerDetails rowItem = getItem(position);

        viewHolder holder ;
        View rowview = convertView;
        if (rowview==null) {

            holder = new viewHolder();
            flater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowview = flater.inflate(R.layout.adapter_vanspinner, null, false);

            holder.mTxtTitle = (TextView) rowview.findViewById(R.id.title);
            rowview.setTag(holder);
        }else{
            holder = (viewHolder) rowview.getTag();
        }
        holder.mTxtTitle.setText(rowItem.getLedgerName());

        return rowview;
    }

    private class viewHolder{
        TextView mTxtTitle;
    }
}

