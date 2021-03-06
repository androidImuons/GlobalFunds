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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.responseModel.DirectRecord;
import com.imuons.globalfunds.utils.MyPreference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DirectUserLevelAdapter extends RecyclerView.Adapter<DirectUserLevelAdapter.UserAdapterolder> {
    Fragment fragment;
    List<DirectRecord> directRecordList;
    private int selected_postion;
    private FragmentActivity activity;

    public DirectUserLevelAdapter(FragmentActivity activity, Fragment fragment, List<DirectRecord> directRecordList) {
        this.fragment = fragment;
        this.directRecordList = directRecordList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public UserAdapterolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_direct_payment, parent, false);
        return new UserAdapterolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterolder holder, int position) {
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
        DirectRecord directRecord = directRecordList.get(position);
        holder.srno.setText(String.valueOf(position + 1));
        holder.fullName.setText(directRecord.getFullname());
        holder.userId.setText(directRecord.getUserId());

        holder.investment.setText(MyPreference.currency_symbol+directRecord.getTotalInvestment());

        holder.date.setText(directRecord.getEntryTime().split(" ")[0].replace("-", "/"));
        if (directRecord.getTotalInvestment() == 0) {

            holder.status.setText("Unpaid");
            holder.status.setTextColor(Color.parseColor("#f44336"));
        } else {
            holder.status.setText("Paid");
            holder.status.setTextColor(Color.parseColor("#006400"));
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
        return directRecordList.size();
    }

    public void updateNotify(List<DirectRecord> directRecordList, int adapterPosition) {
        this.directRecordList = directRecordList;
        notifyDataSetChanged();
        // notifyItemChanged(adapterPosition);
    }

    public void updateList(List<DirectRecord> records) {
        directRecordList = records;
        notifyDataSetChanged();
    }

    public class UserAdapterolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llmain)
        LinearLayout llmain;
        @BindView(R.id.txt_package)
        TextView status;
        @BindView(R.id.txt_address)
        TextView txt_address;
        @BindView(R.id.deposit_id)
        TextView userId;
        @BindView(R.id.amount)
        TextView fullName;
        @BindView(R.id.srno)
        TextView srno;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.investment)
        TextView investment;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;

        @BindView(R.id.expand_icon)
        ImageView expand_icon;


        public UserAdapterolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

//        @OnClick(R.id.expand_icon)
//        void openRow() {
//            ((DirectUserListFragment) fragment).openRow(getAdapterPosition());
//        }
    }
}
