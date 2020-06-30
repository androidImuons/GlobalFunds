package com.imuons.globalfunds.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.dataModel.LevelRecord;
import com.imuons.globalfunds.fragment.DirectUserListFragment;
import com.imuons.globalfunds.fragment.LevelViewFragment;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeveRecordsAdapter extends RecyclerView.Adapter<LeveRecordsAdapter.LevelRecordHolder> {

    Fragment fragment;
    List<LevelRecord> records;

    public LeveRecordsAdapter(Fragment fragment, List<LevelRecord> records) {
        this.fragment = fragment;
        this.records = records;
    }

    @NonNull
    @Override
    public LevelRecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_view, parent, false);
        return new LevelRecordHolder(v);
        //layout_level_view
    }

    @Override
    public void onBindViewHolder(@NonNull LevelRecordHolder holder, int position) {
        LevelRecord levelRecord = records.get(position);
        holder.srno.setText(String.valueOf(position + 1));
        if (levelRecord.getCountry() != null)
            holder.country.setText(levelRecord.getCountry());
        else
            holder.country.setText("");
        holder.fullname.setText(levelRecord.getFullname());
        holder.userid.setText(levelRecord.getUserId());
        holder.Investments.setText(String.valueOf(levelRecord.getTotalInvestment()));
        holder.level.setText(String.valueOf(levelRecord.getLevel()));
        holder.registrationDate.setText(levelRecord.getEntryTime().split(" ")[0].replace("-", "/"));
        holder.sponsorId.setText(levelRecord.getSponserId());
        holder.status.setText(levelRecord.getStatus());
        holder.total.setText(String.valueOf(levelRecord.getTotalInvestment()));

        if (levelRecord.isOpen()) {
            holder.expand_icon.setImageDrawable(fragment.getResources().getDrawable(R.drawable.minus_icon));
            holder.hiddenlayout.setVisibility(View.VISIBLE);
        } else {
            holder.expand_icon.setImageDrawable(fragment.getResources().getDrawable(R.drawable.plus_circle));
            holder.hiddenlayout.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void updateList(List<LevelRecord> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    public void updateNotify(List<LevelRecord> records, int adapterPosition) {
        this.records = records;
        notifyDataSetChanged();
        //notifyItemChanged(adapterPosition);
    }

    public class LevelRecordHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.srno)
        TextView srno;
        @BindView(R.id.userid)
        TextView userid;
        @BindView(R.id.fullname)
        TextView fullname;
        @BindView(R.id.sponsorId)
        TextView sponsorId;
        @BindView(R.id.country)
        TextView country;
        @BindView(R.id.Investments)
        TextView Investments;
        @BindView(R.id.level)
        TextView level;
        @BindView(R.id.registrationDate)
        TextView registrationDate;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.total)
        TextView total;
        @BindView(R.id.expand_icon)
        ImageView expand_icon;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;


        public LevelRecordHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.expand_icon)
        void openRow() {
            ((LevelViewFragment) fragment).openRow(getAdapterPosition());
        }
    }
}
