package com.imuons.globalfunds.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.imuons.globalfunds.R;


public class ROIIncomeReportFragment extends Fragment {

    public ROIIncomeReportFragment() {
        // Required empty public constructor
    }
    public static ROIIncomeReportFragment newInstance() {
        ROIIncomeReportFragment fragment = new ROIIncomeReportFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_r_o_i_income_report, container, false);
    }
}
