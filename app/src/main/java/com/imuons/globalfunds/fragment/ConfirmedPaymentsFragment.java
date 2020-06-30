package com.imuons.globalfunds.fragment;

import android.app.AlertDialog;
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
import com.imuons.globalfunds.adapter.ConnfirmPaymentAdapter;
import com.imuons.globalfunds.responseModel.CommonResponse;
import com.imuons.globalfunds.responseModel.ConfirmPaymentDataModel;
import com.imuons.globalfunds.responseModel.ConfirmPaymentRecordModel;
import com.imuons.globalfunds.responseModel.ConfirmPaymentReportResponse;
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
public class ConfirmedPaymentsFragment extends Fragment implements ConnfirmPaymentAdapter.ClickWithdrwa {

    private View view;
    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;
    @BindView(R.id.spinner_show)
    Spinner spinner_show;
    List<ConfirmPaymentRecordModel> recordList = new ArrayList<>();

    @BindView(R.id.searchbyid)
    EditText searchbyid;
    private ConnfirmPaymentAdapter connfirmPaymentAdapter;

    public ConfirmedPaymentsFragment() {
        // Required empty public constructor
    }

    public static ConfirmedPaymentsFragment newInstance() {
        ConfirmedPaymentsFragment fragment = new ConfirmedPaymentsFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_confirmed_payments, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }
    private void initUI() {
        recycle_view.setHasFixedSize(true);
        recycle_view.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

         connfirmPaymentAdapter=new ConnfirmPaymentAdapter(getActivity(),
                recordList,ConfirmedPaymentsFragment.this );
         recycle_view.setAdapter(connfirmPaymentAdapter);

        searchSpinnner();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPendingPayment("0", "10", "", true);
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

                    getPendingPayment("0", "10", searchbyid.getText().toString(), true);
                    return true;
                }
                return false;
            }
        });

        spinner_show.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getPendingPayment("0", String.valueOf(spinner_show.getSelectedItem()),"", true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void getPendingPayment(String start, String length, String search, boolean search_reg) {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(getActivity());
            Map<String, Object> roiMap = new HashMap<>();
            roiMap.put("start", start);
            roiMap.put("length", length);
            roiMap.put("search[value]", search);
            roiMap.put("search[regex]", false);
            Call call = apiService.GetTopUPReport(roiMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    dialog.dismiss();
                    ConfirmPaymentReportResponse authResponse = (ConfirmPaymentReportResponse) response.body();
                    if (authResponse != null) {
                        Log.i("packgae list::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());
                        } else {
                            //                            setData(authResponse.getData());
                            Toast.makeText(getActivity(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void setData(ConfirmPaymentDataModel data) {
        recordList=data.getRecords();
        connfirmPaymentAdapter.updateList(data.getRecords());
    }

    @Override
    public void clickWithdrwa(ConfirmPaymentRecordModel model) {
       openDialog(model);
    }

    private void callWithdra(ConfirmPaymentRecordModel model) {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(getActivity());
            Map<String, Object> map=new HashMap<>();
            map.put("entry_time", model.getEntryTime());
            map.put("amount",model.getTopAmount());
            map.put("id",model.getSrno());
            Call call = apiService.withdraw_principal(map);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
                    dialog.dismiss();
                    CommonResponse authResponse = (CommonResponse) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            getPendingPayment("0", "10", "", true);
                            Toast.makeText(getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            //                            setData(authResponse.getData());
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

    private void openDialog(ConfirmPaymentRecordModel model) {
        AlertDialog dialogBuilder = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_withdrwa_confirm, null);
        TextView Cancel = dialogView.findViewById(R.id.txt_cancel);
        TextView Submit = dialogView.findViewById(R.id.txt_yes);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callWithdra(model);
                dialogBuilder.dismiss();
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS

                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }
}
