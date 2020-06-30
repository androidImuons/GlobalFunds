package com.imuons.globalfunds.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.imuons.globalfunds.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeWorkingWithdrawalFragment extends Fragment {
    private View view;
    @BindView(R.id.txt_working_income)
    TextView txt_working_income;
    @BindView(R.id.txt_working_withdrwa)
    TextView txt_working_withdrwa;
    @BindView(R.id.txt_working_balance)
    TextView txt_working_balance;
    @BindView(R.id.txt_withdrwa_mode)
    TextView txt_withdrwa_mode;
    @BindView(R.id.txt_address)
    TextView txt_address;
    @BindView(R.id.et_amount)
    EditText et_amount;
    @BindView(R.id.btn_withdrwal)
    TextView btn_withdrwal;




    public MakeWorkingWithdrawalFragment() {
        // Required empty public constructor
    }
    public static MakeWorkingWithdrawalFragment newInstance() {
        MakeWorkingWithdrawalFragment fragment = new MakeWorkingWithdrawalFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_make_working_withdrawal, container, false);
        ButterKnife.bind(this, view);
        return view;

    }
}
