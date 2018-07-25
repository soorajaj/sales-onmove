package com.salestype.view.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.salestype.R;
import com.salestype.adapter.StockListAdapter;
import com.salestype.listener.Updatelis;
import com.salestype.model.StockDetail;
import com.salestype.singletonManager.ObjectFactory;
import com.salestype.utilites.ItemClickSupport;
import com.salestype.view.LandingPage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StockList extends Fragment {


    @BindView(R.id.recyclerview_stocklist)
    RecyclerView mRecyclerStockList;

    @BindView(R.id.relativelayout_button)
    RelativeLayout relativelayout_button;

    @BindView(R.id.textview_goto)
    TextView textview_goto;

    @BindView(R.id.sv_full_search)
    SearchView sv_full_search;

    List<StockDetail> mStockListArrayList;
    private OnItemStartActivityListener listener;
    private Loaddbdata listener1;
    View view;
     ImageView mCloseButton;

    StockListAdapter stockListAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mStockListArrayList = listener1.Loaddata();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view = inflater.inflate(R.layout.fragment_stock_list, container, false);
            ButterKnife.bind(this, view);
             mCloseButton = (ImageView) sv_full_search.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
            mCloseButton.setVisibility(View.INVISIBLE);
            sv_full_search.setIconified(false);
            sv_full_search.setQueryHint("Search for Products");
            sv_full_search.clearFocus();
            if (ObjectFactory.getInstance().getStockmanager(getContext()).getArrayList().size() != 0) {
                relativelayout_button.setVisibility(View.VISIBLE);
            } else {
                relativelayout_button.setVisibility(View.GONE);
            }
             stockListAdapter = new StockListAdapter(this.getActivity(), mStockListArrayList, new Updatelis() {
                 @Override
                 public void mUpdateList(int pos, StockDetail stockDetail) {
                     showInformationAlert(stockDetail, getActivity(),pos);
                 }
             });
            mRecyclerStockList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            mRecyclerStockList.setAdapter(stockListAdapter);
            ItemClickSupport.addTo(mRecyclerStockList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    if (position >= 0) {
                        showInformationAlert(mStockListArrayList.get(position), getActivity(),position);
                    }
                }
            });
            textview_goto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.Loaddata(1);
                }
            });
        }
        sv_full_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sv_full_search.setQuery("",false);
//                stockListAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    mCloseButton.setVisibility(View.INVISIBLE);
                    stockListAdapter.getFilter().filter("");
                }else {
                    mCloseButton.setVisibility(View.VISIBLE);
                    stockListAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });
        return view;
    }

    public void showInformationAlert(final StockDetail stockDetail, final Activity activity, final int position) {
        try {
            final Dialog dialog = new Dialog(activity, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);
            dialog.setContentView(R.layout.dialog_collectdata);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView text_dilog_message = dialog.findViewById(R.id.text_dilog_message);
            TextView textview_itemQty = dialog.findViewById(R.id.textview_qty);
            TextView textview_itemprice = dialog.findViewById(R.id.text_mrp);
            TextView button_cancel = dialog.findViewById(R.id.button_cancel);
            final EditText edittextitem_price = dialog.findViewById(R.id.edittextitem_price);
            edittextitem_price.setVisibility(View.GONE);
            final EditText edittextitem_quandity = dialog.findViewById(R.id.edittextitem_quandity);
            TextView button_ok = dialog.findViewById(R.id.button_ok);
            text_dilog_message.setText(stockDetail.getPNAME());
            edittextitem_quandity.requestFocus();
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            textview_itemQty.setText("Av.Qty: "+stockDetail.getBalances());
            textview_itemprice.setText("MRP: "+stockDetail.getSRate());
            button_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!edittextitem_quandity.getText().toString().equalsIgnoreCase("")) {
                        if (Double.parseDouble(edittextitem_quandity.getText().toString()) <= stockDetail.getBalances()) {
                            StockDetail stockDetail1 = new StockDetail(stockDetail.getProductID(), stockDetail.getPNAME(),
                                    stockDetail.getBatchCode(), Double.parseDouble(edittextitem_quandity.getText().toString()), stockDetail.getSRate(),stockDetail.getBalances());
                            stockDetail.setSelected(true);
                            stockDetail.setSelectedQty(Double.parseDouble(edittextitem_quandity.getText().toString()));
                            ObjectFactory.getInstance().getStockmanager(getActivity()).addOrderItem(0, stockDetail1);
                            stockListAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                            relativelayout_button.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(getActivity(), "Only " + String.valueOf(stockDetail.getBalances()) + " item availabel", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(),"please fill the Quantity field",Toast.LENGTH_SHORT).show();
                    }
//                   listener1.updatelist(position);
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

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemStartActivityListener) {
//            Log.d("Annv - Fragment", "activity " + context.getLocalClassName());
            listener = (OnItemStartActivityListener) context;
        }
        if (context instanceof Loaddbdata) {
//            Log.d("Annv - Fragment", "activity " + context.getLocalClassName());
            listener1 = (Loaddbdata) context;
        }
        if (view != null) {
            if (ObjectFactory.getInstance().getStockmanager(getContext()).getArrayList().size() != 0) {
                relativelayout_button.setVisibility(View.VISIBLE);
            } else {
                relativelayout_button.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Created by sooraj on 21/7/18.
     */
    public interface OnItemStartActivityListener {
        public void Loaddata(int pos);
    }
    public interface Loaddbdata {
        public List<StockDetail> Loaddata();
        public void updatelist(int pos);
    }
}
