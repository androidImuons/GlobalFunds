package com.imuons.globalfunds.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.imuons.globalfunds.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelROIIncomeFragment extends Fragment {

    public LevelROIIncomeFragment() {
        // Required empty public constructor
    }

    public static LevelViewFragment newInstance() {
        LevelViewFragment fragment = new LevelViewFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_r_o_i_income, container, false);
    }
}
