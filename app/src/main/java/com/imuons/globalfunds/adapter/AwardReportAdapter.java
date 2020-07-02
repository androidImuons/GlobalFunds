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
import com.imuons.globalfunds.dataModel.AwardIncomeRecord;
import com.imuons.globalfunds.fragment.AwardIncomeFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AwardReportAdapter extends RecyclerView.Adapter<AwardReportAdapter.ViewHoleder> {


    List<AwardIncomeRecord> records;
    FragmentActivity activity;
    private int selected_postion;

    public AwardReportAdapter(FragmentActivity activity, AwardIncomeFragment awardIncomeFragment, List<AwardIncomeRecord> records) {
        this.activity = activity;
        this.records = records;
    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_award_report, parent, false);
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
        setData(holder,records.get(position),position);
        holder.llmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_postion = position;
                notifyDataSetChanged();

            }
        });
    }

    private void setData(ViewHoleder holder, AwardIncomeRecord awardIncomeRecord, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.txt_award_id.setText(String.valueOf(awardIncomeRecord.getAwardId()));
        holder.award.setText(String.valueOf(awardIncomeRecord.getAward()));
        holder.txt_businesss_req.setText(String.valueOf(awardIncomeRecord.getBusinessRequired()));
        holder.txt_date.setText(awardIncomeRecord.getEntryTime());
        holder.txt_date.setText(awardIncomeRecord.getEntryTime().split(" ")[0].replace("-", "/"));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

  public   void updateList(List<AwardIncomeRecord> records) {
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
        @BindView(R.id.txt_award_id)
        TextView txt_award_id;
        @BindView(R.id.award)
        TextView award;
        @BindView(R.id.txt_businesss_req)
        TextView txt_businesss_req;
        @BindView(R.id.txt_date)
        TextView txt_date;


        public ViewHoleder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}

