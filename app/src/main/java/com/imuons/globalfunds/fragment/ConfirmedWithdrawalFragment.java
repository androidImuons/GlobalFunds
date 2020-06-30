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
public class ConfirmedWithdrawalFragment extends Fragment {

    public ConfirmedWithdrawalFragment() {
        // Required empty public constructor
    }
    public static ConfirmedWithdrawalFragment newInstance() {
        ConfirmedWithdrawalFragment fragment = new ConfirmedWithdrawalFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmed_withdrawal, container, false);
    }
}
