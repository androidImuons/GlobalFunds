package com.imuons.globalfunds.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.imuons.globalfunds.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeNewPaymentFragment extends Fragment {

    private View view;

    public MakeNewPaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_make_new_payment, container, false);
        ButterKnife.bind(this, view);
        return  view;
    }
}
