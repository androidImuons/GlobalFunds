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
import com.imuons.globalfunds.dataModel.PenddingWorkingRecord;
import com.imuons.globalfunds.fragment.OngoingWithdrawalFragment;
import com.imuons.globalfunds.utils.MyPreference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnGoingWithdrawReportAdapter extends RecyclerView.Adapter<OnGoingWithdrawReportAdapter.ViewHoleder> {


    FragmentActivity activity;
    List<PenddingWorkingRecord> records;
    private int selected_postion;


    public OnGoingWithdrawReportAdapter(FragmentActivity activity, OngoingWithdrawalFragment ongoingWithdrawalFragment, List<PenddingWorkingRecord> records) {
        this.activity = activity;
        this.records = records;
    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ongoing_withdrawa, parent, false);
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

    private void setData(ViewHoleder holder, PenddingWorkingRecord penddingWorkingRecord, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.n_w_type.setText(penddingWorkingRecord.getNetworkType());
        holder.amount.setText(MyPreference.currency_symbol + penddingWorkingRecord.getAmount());
        holder.txt_date.setText(penddingWorkingRecord.getEntryTime());
        holder.txt_date.setText(penddingWorkingRecord.getEntryTime().split(" ")[0].replace("-", "/"));
        if (penddingWorkingRecord.getWithdrawType() == 2) {
            holder.txt_withdrwa_type.setText("Working");
        } else if(penddingWorkingRecord.getWithdrawType() == 6){
            holder.txt_withdrwa_type.setText("Principal");

        }else{
            holder.txt_withdrwa_type.setText(String.valueOf(penddingWorkingRecord.getWithdrawType()));
        }
    }

    @Override
    public int getItemCount() {
       // return records.size();
        return (records == null) ? 0 : records.size();
    }

    public void update(List<PenddingWorkingRecord> records) {
        this.records = records;
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
        @BindView(R.id.n_w_type)
        TextView n_w_type;
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.txt_withdrwa_type)
        TextView txt_withdrwa_type;
        @BindView(R.id.txt_date)
        TextView txt_date;


        public ViewHoleder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}

