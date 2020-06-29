package com.imuons.globalfunds.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.fragment.MakeNewPaymentFragment;
import com.imuons.globalfunds.responseModel.PckageDataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PackageItemAdapter extends RecyclerView.Adapter<PackageItemAdapter.ViewHoleder> {
    FragmentActivity activity;
    MakeNewPaymentFragment makeNewPaymentFragment;
    List<PckageDataModel> dataModelList;

    public PackageItemAdapter(FragmentActivity activity, MakeNewPaymentFragment makeNewPaymentFragment, List<PckageDataModel> dataModelList) {
        this.makeNewPaymentFragment = makeNewPaymentFragment;
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_package_view, parent, false);
        return new ViewHoleder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoleder holder, int position) {
        PckageDataModel dataModel = dataModelList.get(position);
        holder.txt_plan_name.setText(dataModel.getName());
        holder.txt_return.setText(dataModel.getRoi() + "% Monthly Returns");
        holder.rb_btc.setText(dataModel.getType());

        holder.btn_make_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.et_amount.getText().toString().trim().isEmpty()){
                    holder.et_amount.setError("Enter Amount");
                    holder.et_amount.requestFocus();
                }else{
                    int amount =Integer.parseInt(holder.et_amount.getText().toString());
                    if(amount>=dataModel.getMinHash()&&amount<=dataModel.getMaxHash()){
                        makeNewPaymentFragment.getAddress(holder.et_amount.getText().toString(),dataModel.getId(),dataModel.getType());
                    }else{
                        holder.et_amount.setError("Enter Min "+dataModel.getMinHash()+ " Max "+dataModel.getMaxHash()+ " Amount");
                        holder.et_amount.requestFocus();
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public void updateList(List<PckageDataModel> dataModelList) {
        this.dataModelList = dataModelList;
        notifyDataSetChanged();
    }

    public class ViewHoleder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_plan_name)
        TextView txt_plan_name;
        @BindView(R.id.txt_return)
        TextView txt_return;
        @BindView(R.id.et_amount)
        EditText et_amount;
        @BindView(R.id.rb_btn)
        RadioButton rb_btc;
        @BindView(R.id.btn_make_payment)
        TextView btn_make_payment;

        public ViewHoleder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
