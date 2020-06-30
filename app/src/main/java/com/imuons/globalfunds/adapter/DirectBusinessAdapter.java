package com.imuons.globalfunds.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.globalfunds.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DirectBusinessAdapter extends RecyclerView.Adapter<DirectBusinessAdapter.ViewHoleder>  {


    public DirectBusinessAdapter(){

    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_direct_income_report, parent, false);
        return new ViewHoleder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoleder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHoleder extends RecyclerView.ViewHolder {
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.expand_icon)
        ImageView expand_icon;
        @BindView(R.id.srno)
        TextView srno;
        @BindView(R.id.txt_direct_business)
        TextView txt_direct_business;




        public ViewHoleder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}

