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
public class OngoingWithdrawalFragment extends Fragment {

    public OngoingWithdrawalFragment() {
        // Required empty public constructor
    }

    public static OngoingWithdrawalFragment newInstance() {
        OngoingWithdrawalFragment fragment = new OngoingWithdrawalFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ongoing_withdrawal, container, false);
    }
}
