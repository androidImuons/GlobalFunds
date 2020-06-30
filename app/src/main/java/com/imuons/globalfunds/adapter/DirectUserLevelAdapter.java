package com.imuons.globalfunds.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.fragment.DirectUserListFragment;
import com.imuons.globalfunds.responseModel.DirectRecord;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DirectUserLevelAdapter extends RecyclerView.Adapter<DirectUserLevelAdapter.UserAdapterolder> {
    Fragment fragment;
    List<DirectRecord> directRecordList;

    public DirectUserLevelAdapter(Fragment fragment, List<DirectRecord> directRecordList) {
        this.fragment = fragment;
        this.directRecordList = directRecordList;
    }

    @NonNull
    @Override
    public UserAdapterolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_direct_payment, parent, false);
        return new UserAdapterolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterolder holder, int position) {
        DirectRecord directRecord = directRecordList.get(position);
        holder.srno.setText(String.valueOf(position + 1));
        holder.fullName.setText(directRecord.getFullname());
        holder.userId.setText(directRecord.getUserId());
        holder.userId.setText(directRecord.getUserId());
        if (directRecord.getTotalInvestment() == 0) {

            holder.status.setText("Unpaid");
            holder.status.setTextColor(Color.parseColor("#f44336"));
        } else {
            holder.status.setText("Paid");
            holder.status.setTextColor(Color.parseColor("#006400"));
        }

        if (directRecord.isOpen()) {
            holder.expand_icon.setImageDrawable(fragment.getResources().getDrawable(R.drawable.minus_icon));
            holder.hiddenlayout.setVisibility(View.VISIBLE);
        } else {
            holder.expand_icon.setImageDrawable(fragment.getResources().getDrawable(R.drawable.plus_circle));
            holder.hiddenlayout.setVisibility(View.GONE);
        }

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

        @BindView(R.id.txt_package)
        TextView status;
        @BindView(R.id.txt_address)
        TextView txt_address;
        @BindView(R.id.txt_date)
        TextView txt_date;
        @BindView(R.id.deposit_id)
        TextView userId;
        @BindView(R.id.amount)
        TextView fullName;
        @BindView(R.id.srno)
        TextView srno;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;

        @BindView(R.id.expand_icon)
        ImageView expand_icon;


        public UserAdapterolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.expand_icon)
        void openRow() {
            ((DirectUserListFragment) fragment).openRow(getAdapterPosition());
        }
    }
}
