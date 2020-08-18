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
import com.imuons.globalfunds.adapter.LeveRecordsAdapter;
import com.imuons.globalfunds.dataModel.LevelData;
import com.imuons.globalfunds.dataModel.LevelRecord;
import com.imuons.globalfunds.dataModel.LevelViewData;
import com.imuons.globalfunds.responseModel.GetLevelResponse;
import com.imuons.globalfunds.responseModel.LevelViewResponseModel;
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
public class LevelViewFragment extends Fragment {

    @BindView(R.id.getselectedlevel)
    Spinner getselectedlevel;
    @BindView(R.id.recycler_directuser_report)
    RecyclerView recycle_view;
    @BindView(R.id.spinner_show)
    Spinner spinner_show;
    @BindView(R.id.searchbyid)
    EditText searchbyid;
    List<LevelRecord> records;
    List<LevelData> levelData;
    LeveRecordsAdapter leveRecordsAdapter;

    public LevelViewFragment() {
        // Required empty public constructor
    }

    public static LevelViewFragment newInstance() {
        LevelViewFragment fragment = new LevelViewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_level_view, container, false);
        ButterKnife.bind(this, view);
        initUI();
        callGetLevelView();

        return view;
    }

    private void initUI() {
        recycle_view.setHasFixedSize(true);
        recycle_view.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        records = new ArrayList<>();
        leveRecordsAdapter = new LeveRecordsAdapter(getActivity(), LevelViewFragment.this, records);
        recycle_view.setAdapter(leveRecordsAdapter);


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

                    //levelData.get(getselectedlevel.getSelectedItemPosition()).getLevelId();
                    records.clear();
                    leveRecordsAdapter.updateList(records);
                    callLevelViewApi("0", "10", searchbyid.getText().toString(), String.valueOf(levelData.get(getselectedlevel.getSelectedItemPosition()).getLevelId()));
                    return true;
                }
                return false;
            }
        });

        spinner_show.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //                records.clear();
                leveRecordsAdapter.updateList(records);
                Log.d("onItemSelected", "--" + levelData.get(getselectedlevel.getSelectedItemPosition()).getLevelId());
                callLevelViewApi("0", String.valueOf(spinner_show.getSelectedItem()), "", String.valueOf(levelData.get(getselectedlevel.getSelectedItemPosition()).getLevelId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getselectedlevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                records.clear();
                leveRecordsAdapter.updateList(records);
                Log.d(" level  onItemSelected", "--" + levelData.get(getselectedlevel.getSelectedItemPosition()).getLevelId());
                callLevelViewApi("0", String.valueOf(spinner_show.getSelectedItem()), "", String.valueOf(levelData.get(position).getLevelId()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void callLevelViewApi(String start, String length, String search, String leveId) {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(getActivity());
            Map<String, Object> roiMap = new HashMap<>();
            roiMap.put("start", start);
            roiMap.put("length", 10);
            roiMap.put("level_id", leveId);
            roiMap.put("search[value]", search);
            roiMap.put("search[regex]", false);
            Log.d("leve req", "----" + roiMap.toString());
            Call call = apiService.levelViewApi(roiMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    dialog.dismiss();
                    LevelViewResponseModel authResponse = (LevelViewResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("level reposn", new Gson().toJson(authResponse));
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
                    Log.i("error ::", t.getMessage());
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    // Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void setData(LevelViewData data) {
        records = data.getRecords();
        leveRecordsAdapter.updateList(records);
    }

    private void callGetLevelView() {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(getActivity());
            Call call = apiService.GetlevelViewApi();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    dialog.dismiss();
                    GetLevelResponse authResponse = (GetLevelResponse) response.body();
                    if (authResponse != null) {
                        Log.i("get level list::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setSpinner(authResponse.getData());
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
                    Log.i("error level ::", t.getMessage());
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSpinner(List<LevelData> data) {
        levelData = data;
        ArrayAdapter<LevelData> adapter = new ArrayAdapter<LevelData>(getContext(), android.R.layout.simple_spinner_dropdown_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        getselectedlevel.setAdapter(adapter);
        if (levelData.size() != 0)
            searchSpinnner();
    }

    public void openRow(int adapterPosition) {
        if (records.get(adapterPosition).isOpen()) {
            records.get(adapterPosition).setOpen(false);
        } else
            records.get(adapterPosition).setOpen(true);
        leveRecordsAdapter.updateNotify(records, adapterPosition);
    }
}
