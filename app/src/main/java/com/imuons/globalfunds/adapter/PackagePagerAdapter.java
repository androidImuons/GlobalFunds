package com.imuons.globalfunds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.fragment.MakeNewPaymentFragment;
import com.imuons.globalfunds.responseModel.PckageDataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PackagePagerAdapter extends PagerAdapter {
    List<PckageDataModel> dataModelList;
    Context context;
    private MakeNewPaymentFragment makeNewPaymentFragment;

    public PackagePagerAdapter(Context context, List<PckageDataModel> dataModelList, MakeNewPaymentFragment makeNewPaymentFragment) {
        this.context = context;
        this.dataModelList = dataModelList;
        this.makeNewPaymentFragment = makeNewPaymentFragment;
    }

    @Override
    public int getCount() {
        return dataModelList.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = null;
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_deposit_payment, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
            container.addView(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        setData(viewHolder, dataModelList.get(position), position);

        return view;
    }


    private void setData(ViewHolder viewHolder, PckageDataModel pckageDataModel, int position) {
        viewHolder.txt_plan_name.setText(pckageDataModel.getName());
        viewHolder.txt_min_mak_limit.setText("Min " + pckageDataModel.getMinHash() + " to Max " + pckageDataModel.getMaxHash());
        viewHolder.txt_txt_return.setText(pckageDataModel.getRoi() + "%");
        viewHolder.txt_txt_locking_day.setText(pckageDataModel.getDurationMonth() + " Month");
        viewHolder.txt_txt_withdrawal_day.setText("1st of every month");
        viewHolder.txt_principle_withdrawal_day.setText("1st of the month");

        if (position % 2 != 0) {
            viewHolder.ll_top_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_top_round_green_even));
            viewHolder.ll_center_first_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_green_gardient_even));
            viewHolder.ll_center_second_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_green_plan_gradin_even));
            viewHolder.ll_center_third_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_green_plan__dark_gradin_even));
            viewHolder.ll_center_four_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_green_plan_gradin_even));
            viewHolder.ll_center_five_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_green_plan__dark_gradin_even));
            viewHolder.ll_center_six_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_bottom_round_green_gardine_even));
        } else {
            viewHolder.ll_top_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_top_round_green));
            viewHolder.ll_center_first_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_green_gardient));
            viewHolder.ll_center_second_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_green_plan_gradin));
            viewHolder.ll_center_third_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_green_plan__dark_gradin));
            viewHolder.ll_center_four_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_green_plan_gradin));
            viewHolder.ll_center_five_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_green_plan__dark_gradin));
            viewHolder.ll_center_six_layer.setBackground(context.getResources().getDrawable(R.drawable.bg_bottom_round_green_gardine));
        }


        viewHolder.btn_make_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewHolder.et_amount.getText().toString().trim().isEmpty()) {
                    viewHolder.et_amount.setError("Enter Amount");
                    viewHolder.et_amount.requestFocus();
                } else {
                    int amount = Integer.parseInt(viewHolder.et_amount.getText().toString());
                    if (amount >= pckageDataModel.getMinHash() && amount <= pckageDataModel.getMaxHash()) {
                        makeNewPaymentFragment.getAddress(viewHolder.et_amount.getText().toString(), pckageDataModel.getId(), pckageDataModel.getCurrencyCode());
                    } else {
                        viewHolder.et_amount.setError("Enter Min " + pckageDataModel.getMinHash() + " Max " + pckageDataModel.getMaxHash() + " Amount");
                        viewHolder.et_amount.requestFocus();
                    }
                }

            }
        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    public void update(List<PckageDataModel> dataModelList) {
        this.dataModelList = dataModelList;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        @BindView(R.id.txt_plan_name)
        TextView txt_plan_name;
        @BindView(R.id.txt_min_mak_limit)
        TextView txt_min_mak_limit;

        @BindView(R.id.txt_txt_return)
        TextView txt_txt_return;
        @BindView(R.id.txt_txt_locking_day)
        TextView txt_txt_locking_day;
        @BindView(R.id.txt_txt_withdrawal_day)
        TextView txt_txt_withdrawal_day;
        @BindView(R.id.txt_principle_withdrawal_day)
        TextView txt_principle_withdrawal_day;
        @BindView(R.id.et_amount)
        EditText et_amount;
        @BindView(R.id.btn_make_payment)
        TextView btn_make_payment;
        @BindView(R.id.ll_top_layer)
        LinearLayout ll_top_layer;
        @BindView(R.id.ll_center_first_layer)
        LinearLayout ll_center_first_layer;
        @BindView(R.id.ll_center_second_layer)
        LinearLayout ll_center_second_layer;
        @BindView(R.id.ll_center_third_layer)
        LinearLayout ll_center_third_layer;

        @BindView(R.id.ll_center_four_layer)
        LinearLayout ll_center_four_layer;
        @BindView(R.id.ll_center_five_layer)
        LinearLayout ll_center_five_layer;
        @BindView(R.id.ll_center_six_layer)
        LinearLayout ll_center_six_layer;
        @BindView(R.id.iv_doen_arrow)
        ImageView iv_doen_arrow;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
