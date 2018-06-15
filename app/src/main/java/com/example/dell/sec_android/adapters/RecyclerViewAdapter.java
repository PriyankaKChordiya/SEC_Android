package com.example.dell.sec_android.adapters;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.dell.sec_android.R;

import com.example.dell.sec_android.pojos.ZillaParishad;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<ZillaParishad> zpList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.srN0TV) TextView srN0TV;
        @BindView(R.id.registrationNoTV)TextView registrationNoTV;
        @BindView(R.id.fullNameTV)TextView fullNameTV;
        @BindView(R.id.edNameTV)TextView edNameTV;
        @BindView(R.id.edIDTV)TextView edIDTV;
        @BindView(R.id.downloadTV)TextView downloadTV;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }


    public RecyclerViewAdapter(ArrayList<ZillaParishad> list, Context context) {
        this.zpList = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_view, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ZillaParishad list = zpList.get(position);
        holder.srN0TV.setText(list.getSr_no());
        holder.registrationNoTV.setText(list.getRegisteration_no());
        holder.fullNameTV.setText(list.getFull_name());
        holder.edNameTV.setText(list.getName_of_electoral_div());
        holder.edIDTV.setText(list.getElectoral_div_id());
        holder.downloadTV.setText(list.getDownload());
    }

    @Override
    public int getItemCount() {
        return zpList.size();
    }
}
