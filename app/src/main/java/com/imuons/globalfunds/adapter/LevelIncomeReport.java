package com.imuons.globalfunds.adapter;

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
import com.imuons.globalfunds.fragment.LevelIncomeReportFragment;
import com.imuons.globalfunds.responseModel.LevelIncomeRecordModel;
import com.imuons.globalfunds.utils.MyPreference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LevelIncomeReport extends RecyclerView.Adapter<LevelIncomeReport.ViewHoleder> {

    FragmentActivity activity;
    List<LevelIncomeRecordModel> recordModelList;
    private int selected_postion;

    public LevelIncomeReport(FragmentActivity activity, LevelIncomeReportFragment levelIncomeReportFragment, List<LevelIncomeRecordModel> recordModelList) {
        this.activity = activity;
        this.recordModelList = recordModelList;
    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_level_roi_income_report, parent, false);
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
        setdata(holder, recordModelList.get(position), position);
        holder.llmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_postion = position;
                notifyDataSetChanged();

            }
        });
    }

    private void setdata(ViewHoleder holder, LevelIncomeRecordModel levelIncomeRecordModel, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.txt_user_id.setText(levelIncomeRecordModel.getFromUserId());
        holder.txt_name.setText(levelIncomeRecordModel.getFromFullname());
        holder.txt_date.setText(levelIncomeRecordModel.getEntryTime());
        holder.txt_amount.setText(MyPreference.currency_symbol + levelIncomeRecordModel.getAmount());
        holder.txt_laps_amount.setText(String.valueOf(levelIncomeRecordModel.getLapsAmount()));
        holder.txt_topup_id.setText(levelIncomeRecordModel.getPin());
        holder.txt_status.setText(levelIncomeRecordModel.getStatus());

    }

    @Override
    public int getItemCount() {
        return recordModelList.size();
    }

    public void update(List<LevelIncomeRecordModel> recordModelList) {
        this.recordModelList = recordModelList;
        notifyDataSetChanged();
    }

    public class ViewHoleder extends RecyclerView.ViewHolder {
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        @BindView(R.id.llmain)
        LinearLayout llmain;
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
        @BindView(R.id.expand_icon)
        ImageView expand_icon;
        @BindView(R.id.txt_laps_amount)
        TextView txt_laps_amount;
        @BindView(R.id.txt_status)
        TextView txt_status;
        @BindView(R.id.txt_topup_id)
        TextView txt_topup_id;


        public ViewHoleder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}

