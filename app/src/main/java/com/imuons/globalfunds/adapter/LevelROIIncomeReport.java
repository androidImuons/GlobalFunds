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
import com.imuons.globalfunds.fragment.LevelROIIncomeFragment;
import com.imuons.globalfunds.responseModel.LevelROIIncomeRecordModel;
import com.imuons.globalfunds.utils.MyPreference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LevelROIIncomeReport extends RecyclerView.Adapter<LevelROIIncomeReport.ViewHoleder> {

    FragmentActivity activity;
    List<LevelROIIncomeRecordModel> recordModelList;
    private int selected_postion;

    public LevelROIIncomeReport(FragmentActivity activity, LevelROIIncomeFragment levelROIIncomeFragment, List<LevelROIIncomeRecordModel> recordModelList) {
        this.activity = activity;
        this.recordModelList = recordModelList;
    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_level_roi_income, parent, false);
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
        setData(holder, recordModelList.get(position), position);
        holder.llmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_postion = position;
                notifyDataSetChanged();

            }
        });
    }

    private void setData(ViewHoleder holder, LevelROIIncomeRecordModel levelROIIncomeRecordModel, int position) {
        holder.srno.setText(String.valueOf(position + 1));

        holder.txt_user_id.setText(levelROIIncomeRecordModel.getFromUserId());
        holder.txt_name.setText(levelROIIncomeRecordModel.getFromFullname());
        holder.txt_amount.setText(MyPreference.currency_symbol+levelROIIncomeRecordModel.getAmount());

        holder.txt_date.setText(levelROIIncomeRecordModel.getEntryTime().split(" ")[0].replace("-", "/"));
        if (levelROIIncomeRecordModel.getStatus().equals("Paid")) {
            holder.txt_status.setText(levelROIIncomeRecordModel.getStatus());
            holder.txt_status.setTextColor(Color.parseColor("#1D7F6E"));
        } else {
            holder.txt_status.setText(levelROIIncomeRecordModel.getStatus());
            holder.txt_status.setTextColor(Color.parseColor("#F30505"));
        }
    }

    @Override
    public int getItemCount() {
        return recordModelList.size();
    }

    public void update(List<LevelROIIncomeRecordModel> recordModelList) {
        this.recordModelList = recordModelList;
        notifyDataSetChanged();
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
        @BindView(R.id.txt_user_id)
        TextView txt_user_id;
        @BindView(R.id.txt_amount)
        TextView txt_amount;
        @BindView(R.id.txt_name)
        TextView txt_name;
        @BindView(R.id.txt_date)
        TextView txt_date;
        @BindView(R.id.txt_status)
        TextView txt_status;


        public ViewHoleder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}

