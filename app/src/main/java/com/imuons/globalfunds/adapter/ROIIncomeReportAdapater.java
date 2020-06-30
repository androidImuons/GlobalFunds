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

public class ROIIncomeReportAdapater extends RecyclerView.Adapter<ROIIncomeReportAdapater.ViewHoleder>  {


    public ROIIncomeReportAdapater(){

    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_roi_income_report, parent, false);
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
        @BindView(R.id.srno)
        TextView srno;
        @BindView(R.id.deposit_id)
        TextView deposit_id;
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.txt_package)
        TextView txt_package;
        @BindView(R.id.txt_date)
        TextView txt_date;
        @BindView(R.id.expand_icon)
        ImageView expand_icon;
        @BindView(R.id.txt_roi_amount)
        TextView txt_roi_amount;
        @BindView(R.id.txt_status)
        TextView txt_status;


        public ViewHoleder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}

