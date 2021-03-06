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
import com.imuons.globalfunds.fragment.OnGoingPayments;
import com.imuons.globalfunds.responseModel.OnGoingPaymentRecord;
import com.imuons.globalfunds.utils.MyPreference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnGoingPaymentAdapter extends RecyclerView.Adapter<OnGoingPaymentAdapter.ViewHoleder> {

    FragmentActivity activity;
    OnGoingPayments onGoingPayments;
    List<OnGoingPaymentRecord> goingPaymentRecordList;
    private int selected_postion;

    public OnGoingPaymentAdapter(FragmentActivity activity, OnGoingPayments onGoingPayments, List<OnGoingPaymentRecord> recordList) {
        this.activity = activity;
        this.onGoingPayments = onGoingPayments;
        this.goingPaymentRecordList = recordList;
    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pending_payment, parent, false);
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
      OnGoingPaymentRecord record= goingPaymentRecordList.get(position);
        setData(holder,record,position);
        holder.llmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_postion = position;
                notifyDataSetChanged();

            }
        });

    }

    private void setData(ViewHoleder holder, OnGoingPaymentRecord record, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.txt_address.setText(record.getAddress());
        holder.txt_date.setText(record.getEntryTime().split(" ")[0].replace("-", "/"));
        holder.deposit_id.setText(record.getInvoiceId());
        holder.txt_package.setText(record.getPlanName());
        holder.amount.setText(MyPreference.currency_symbol +String.valueOf(record.getPriceInUsd()));
    }

    @Override
    public int getItemCount() {
        return goingPaymentRecordList.size();
    }

    public void updateList(List<OnGoingPaymentRecord> records) {
        goingPaymentRecordList = records;
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
        @BindView(R.id.txt_address)
        TextView txt_address;
        @BindView(R.id.txt_date)
        TextView txt_date;
        @BindView(R.id.expand_icon)
        ImageView expand_icon;


        public ViewHoleder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
