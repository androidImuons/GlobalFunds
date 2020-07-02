package com.imuons.globalfunds.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.dataModel.RoiIncomeRecord;
import com.imuons.globalfunds.fragment.ROIIncomeReportFragment;
import com.imuons.globalfunds.utils.MyPreference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ROIIncomeReportAdapater extends RecyclerView.Adapter<ROIIncomeReportAdapater.ViewHoleder> {


    FragmentActivity activity;
    List<RoiIncomeRecord> records;
    private int selected_postion;

    public ROIIncomeReportAdapater(FragmentActivity activity, ROIIncomeReportFragment roiIncomeReportFragment, List<RoiIncomeRecord> records) {
        this.activity = activity;
        this.records = records;
    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_roi_income_report, parent, false);
        return new ViewHoleder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoleder holder, int position) {
        holder.hiddenlayout.setVisibility(View.GONE);
        if (selected_postion == position) {
            holder.expand_icon.setSelected(true);
            holder.expand_icon.setActivated(true);
            holder.llmain.setActivated(true);
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(activity, R.anim.slide_down);
            //toggling visibility
            holder.hiddenlayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.hiddenlayout.startAnimation(slideDown);
        } else {
            holder.llmain.setSelected(false);
            holder.expand_icon.setActivated(false);
            holder.llmain.setActivated(false);
        }
        setData(holder, records.get(position), position);
        holder.llmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_postion = position;
                notifyDataSetChanged();

            }
        });
    }

    private void setData(ViewHoleder holder, RoiIncomeRecord roiIncomeRecord, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.deposit_id.setText(roiIncomeRecord.getPin());
        holder.amount.setText(MyPreference.currency_symbol + roiIncomeRecord.getOnAmount());
        holder.txt_package.setText(roiIncomeRecord.getName());
        holder.txt_status.setText(roiIncomeRecord.getStatus());
        holder.txt_roi_amount.setText(MyPreference.currency_symbol + roiIncomeRecord.getAmount());
        holder.txt_date.setText(roiIncomeRecord.getEntryTime().split(" ")[0].replace("-", "/"));
        if (roiIncomeRecord.getStatus().equals("Paid")) {
            holder.txt_status.setText("Paid");
            holder.txt_status.setTextColor(Color.parseColor("#1D7F6E"));
        } else {
            holder.txt_status.setText("Unpaid");
            holder.txt_status.setTextColor(Color.parseColor("#F30505"));
        }

    }


    @Override
    public int getItemCount() {
        return records.size();
    }

    public void updateList(List<RoiIncomeRecord> records) {
        this.records = records;
        notifyDataSetChanged();
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

