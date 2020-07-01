package com.imuons.globalfunds.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.imuons.globalfunds.R;
import com.imuons.globalfunds.adapter.OnGoingWithdrawReportAdapter;
import com.imuons.globalfunds.dataModel.PenddingWithdrwalData;
import com.imuons.globalfunds.dataModel.PenddingWorkingRecord;
import com.imuons.globalfunds.responseModel.PenddingWithdrwalResponse;
import com.imuons.globalfunds.retrofit.AppService;
import com.imuons.globalfunds.retrofit.ServiceGenerator;
import com.imuons.globalfunds.utils.AppCommon;
import com.imuons.globalfunds.utils.ViewUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OngoingWithdrawalFragment extends Fragment {
    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;
    @BindView(R.id.spinner_show)
    Spinner spinner_show;
    @BindView(R.id.searchbyid)
    EditText searchbyid;
    List<PenddingWorkingRecord> records = new ArrayList<>();
    private View view;
    private OnGoingWithdrawReportAdapter ongoingWithdrawAdapter;

    public OngoingWithdrawalFragment() {
        // Required empty public constructor
    }

    public static OngoingWithdrawalFragment newInstance() {
        OngoingWithdrawalFragment fragment = new OngoingWithdrawalFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_ongoing_withdrawal, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        recycle_view.setHasFixedSize(true);
        recycle_view.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        ongoingWithdrawAdapter = new OnGoingWithdrawReportAdapter(getActivity(), OngoingWithdrawalFragment.this, records);
        recycle_view.setAdapter(ongoingWithdrawAdapter);

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

                    callpendingOutgoingApi("0", "10", searchbyid.getText().toString());
                    return true;
                }
                return false;
            }
        });

        spinner_show.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                callpendingOutgoingApi("0", String.valueOf(spinner_show.getSelectedItem()), "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        callpendingOutgoingApi("0", "10", "");
    }


    public void callpendingOutgoingApi(String start, String length, String search) {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(getActivity());
            Map<String, Object> roiMap = new HashMap<>();
            roiMap.put("start", start);
            roiMap.put("length", length);
            roiMap.put("search[value]", search);
            roiMap.put("search[regex]", false);
            roiMap.put("withdraw_type", 2);
            Call call = apiService.withDrawPendingApi(roiMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
                    dialog.dismiss();
                    PenddingWithdrwalResponse authResponse = (PenddingWithdrwalResponse) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());
                        } else {

                            Toast.makeText(getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(PenddingWithdrwalData data) {
        records = data.getRecords();
        ongoingWithdrawAdapter.update(records);
    }
}
