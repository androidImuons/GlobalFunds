package com.imuons.globalfunds.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.dataModel.DirectBusinessRecord;
import com.imuons.globalfunds.fragment.DirectIncomeReport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DirectBusinessAdapter extends RecyclerView.Adapter<DirectBusinessAdapter.ViewHoleder> {

    FragmentActivity activity;
    List<DirectBusinessRecord> records;
    private int selected_postion;

    public DirectBusinessAdapter(FragmentActivity activity, DirectIncomeReport directIncomeReport, List<DirectBusinessRecord> records) {
        this.activity = activity;
        this.records = records;
    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_direct_income_report, parent, false);
        return new ViewHoleder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoleder holder, int position) {
//        holder.hiddenlayout.setVisibility(View.GONE);
//        if (selected_postion == position) {
//            holder.expand_icon.setSelected(true);
//            holder.expand_icon.setActivated(true);
//            holder.llmain.setActivated(true);
//            //creating an animation
//            Animation slideDown = AnimationUtils.loadAnimation(activity, R.anim.slide_down);
//            //toggling visibility
//            holder.hiddenlayout.setVisibility(View.VISIBLE);
//
//            //adding sliding effect
//            holder.hiddenlayout.startAnimation(slideDown);
//        } else {
//            holder.llmain.setSelected(false);
//            holder.expand_icon.setActivated(false);
//            holder.llmain.setActivated(false);
//        }
        setdata(holder, records.get(position), position);
        holder.llmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_postion = position;
               // notifyDataSetChanged();

            }
        });
    }

    private void setdata(ViewHoleder holder, DirectBusinessRecord directBusinessRecord, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.txt_direct_business.setText(String.valueOf(directBusinessRecord.getTotalBusiness()));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void updateList(List<DirectBusinessRecord> records) {
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
        @BindView(R.id.txt_direct_business)
        TextView txt_direct_business;


        public ViewHoleder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}

