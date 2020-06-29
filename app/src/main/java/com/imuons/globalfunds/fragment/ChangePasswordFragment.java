package com.imuons.globalfunds.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.imuons.globalfunds.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {

    @BindView(R.id.et_OldPassword)
    EditText et_OldPassword;
    @BindView(R.id.et_newPassword)
    EditText et_newPassword;
    @BindView(R.id.et_reTypePassword)
    EditText et_reTypePassword;
    @BindView(R.id.btnUpdate)
    EditText btnUpdate;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        ButterKnife.bind(this, view);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_password = et_newPassword.getText().toString().trim();
                String cmf_password = et_reTypePassword.getText().toString().trim();
                String old_password = et_OldPassword.getText().toString().trim();

                if (old_password.isEmpty()) {
                    et_OldPassword.setError("Please enter your old password");
                } else if (cmf_password.isEmpty()) {
                    et_reTypePassword.setError("Please enter confirm password");
                } else if (new_password.isEmpty()) {
                    et_newPassword.setError("Please enter new password");
                } else if (!cmf_password.matches(new_password)) {
                    et_reTypePassword.setError("password not match");
                }
            }
        });

        return view;
    }
}
