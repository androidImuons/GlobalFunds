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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.dataModel.ConfirWithdralList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmWithdrawReportAdapter extends RecyclerView.Adapter<ConfirmWithdrawReportAdapter.ViewHoleder> {



    Fragment fragment;
    List<ConfirWithdralList> confirWithdralLists;
    private int selected_postion;

    public ConfirmWithdrawReportAdapter(Fragment fragment, List<ConfirWithdralList> confirWithdralLists) {
        this.fragment = fragment;
        this.confirWithdralLists = confirWithdralLists;
    }


    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_confirm_withdrawa, parent, false);
        return new ViewHoleder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoleder holder, int position)  {
        holder.hiddenlayout.setVisibility(View.GONE);
        ConfirWithdralList confirWithdralList = confirWithdralLists.get(position);
        holder.srno.setText(String.valueOf(position+1));
        holder.amount.setText(String.valueOf(confirWithdralList.getAmount()));
        holder.n_w_type.setText(confirWithdralList.getNetworkType());
        holder.txt_withdrwa_type.setText(String.valueOf(confirWithdralList.getWithdrawType()));
        holder.txtremark.setText(confirWithdralList.getRemark());
        if(confirWithdralList.getStatus().equals("Paid")){
            holder.txtremark.setText("Paid");
            holder.txtremark.setTextColor(Color.parseColor("#1D7F6E"));
        }else{
            holder.txtremark.setText("Unpaid");
            holder.txtremark.setTextColor(Color.parseColor("#F30505"));
        }

        holder.txt_date.setText(confirWithdralList.getEntryTime().split(" ")[0].replace("-", "/"));
        if (selected_postion == position) {
            holder.expand_icon.setSelected(true);
            holder.expand_icon.setActivated(true);
            holder.llmain.setActivated(true);
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(fragment.getContext(), R.anim.slide_down);
            //toggling visibility
            holder.hiddenlayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.hiddenlayout.startAnimation(slideDown);
        } else {
            holder.llmain.setSelected(false);
            holder.expand_icon.setActivated(false);
            holder.llmain.setActivated(false);
        }

        holder.llmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_postion = position;
                notifyDataSetChanged();

            }
        });
    }



    @Override
    public int getItemCount() {
        return confirWithdralLists.size();
    }

  public   void updateList(List<ConfirWithdralList> records) {
        this.confirWithdralLists = records;
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
        @BindView(R.id.txtremark)
        TextView txtremark;
        @BindView(R.id.txt_date)
        TextView txt_date;


        public ViewHoleder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}

