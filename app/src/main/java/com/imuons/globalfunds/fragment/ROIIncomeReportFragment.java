package com.imuons.globalfunds.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.adapter.ROIIncomeReportAdapater;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ROIIncomeReportFragment extends Fragment {

    private View view;

    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;
    @BindView(R.id.spinner_show)
    Spinner spinner_show;
    @BindView(R.id.searchbyid)
    EditText searchbyid;

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
        view =inflater.inflate(R.layout.fragment_r_o_i_income_report, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return  view;
    }

    private void initUI() {
        recycle_view.setHasFixedSize(true);
        recycle_view.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        ROIIncomeReportAdapater roiIncomeReportAdapater = new ROIIncomeReportAdapater(getActivity(),
                ROIIncomeReportFragment.this);
        recycle_view.setAdapter(roiIncomeReportAdapater);

        searchSpinnner();
    }

    private void searchSpinnner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.show_array, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinner_show.setAdapter(adapter);
        spinner_show.setSelection(0);


        searchbyid.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    getList("0", "10", searchbyid.getText().toString(), true);
                    return true;
                }
                return false;
            }
        });

        spinner_show.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getList("0", String.valueOf(spinner_show.getSelectedItem()),"", true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getList(String s, String valueOf, String s1, boolean b) {

    }
}
