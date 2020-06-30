package com.imuons.globalfunds.fragment;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.imuons.globalfunds.R;
import com.imuons.globalfunds.responseModel.DashBoardDataModel;
import com.imuons.globalfunds.responseModel.DashBoardResponseModel;
import com.imuons.globalfunds.responseModel.ReferalLinkResponseModel;
import com.imuons.globalfunds.retrofit.AppService;
import com.imuons.globalfunds.retrofit.ServiceGenerator;
import com.imuons.globalfunds.utils.AppCommon;
import com.imuons.globalfunds.utils.MyPreference;
import com.imuons.globalfunds.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.txt_link)
    TextView txtlink;
    @BindView(R.id.txt_total_deposit)
    TextView txt_total_deposit;
    @BindView(R.id.txt_total_income)
    TextView txt_total_income;
    @BindView(R.id.txt_total_withdrawa)
    TextView txt_total_withdrawa;
    @BindView(R.id.txt_total_balanace)
    TextView txt_total_balanace;
    @BindView(R.id.txt_roi_icome)
    TextView txt_roi_icome;
    @BindView(R.id.txt_direct_ref_income)
    TextView txt_direct_ref_income;
    @BindView(R.id.txt_team_roi_income)
    TextView txt_team_roi_income;
    @BindView(R.id.txt_level_income)
    TextView txt_level_income;
    @BindView(R.id.txt_copy)
    TextView txt_copy;
    String link ;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private DashBoardDataModel data;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
//       link= "https://www.globalfundsgroup.co/global-funds/user#/register?ref_id=" + AppCommon.getInstance(getActivity()).getUserId();
//
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDashBoard();
        getReferal();
    }

    public void getDashBoard() {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(HomeFragment.this.getActivity());
            Call call = apiService.GetDashBoard();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(HomeFragment.this.getContext()).clearNonTouchableFlags(HomeFragment.this.getActivity());
                    dialog.dismiss();
                    DashBoardResponseModel authResponse = (DashBoardResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());
                        } else {
//                            setData(authResponse.getData());
                            Toast.makeText(HomeFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void
    getReferal() {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(HomeFragment.this.getActivity());
            Call call = apiService.GetReferal();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(HomeFragment.this.getContext()).clearNonTouchableFlags(HomeFragment.this.getActivity());
                    dialog.dismiss();
                    ReferalLinkResponseModel authResponse = (ReferalLinkResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                           link=authResponse.getData().getLink();
                            txtlink.setText(link);
                        } else {
                            //                            setData(authResponse.getData());
                            Toast.makeText(HomeFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
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



    private void setData(DashBoardDataModel data) {
        this.data = data;
        txt_total_income.setText(MyPreference.currency_symbol + String.format("%.2f", data.getWorkingWallet()));
        txt_total_deposit.setText(MyPreference.currency_symbol + data.getTotalInvestment());
        txt_total_withdrawa.setText(MyPreference.currency_symbol + data.getTotalWithdraw());
        txt_total_balanace.setText(MyPreference.currency_symbol + String.format("%.2f", data.getWorkingWalletBalance()));
        txt_roi_icome.setText(MyPreference.currency_symbol + data.getRoiIncome());
        txt_direct_ref_income.setText(MyPreference.currency_symbol + String.format("%.2f", data.getTotal_business()));
        txt_team_roi_income.setText(MyPreference.currency_symbol + data.getRoiIncome());
        txt_level_income.setText(MyPreference.currency_symbol + String.format("%.2f", data.getLevelIncome()));
    }

    @OnClick(R.id.txt_copy)
    public void copyLink() {

        if (data != null) {

            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", link);
            clipboard.setPrimaryClip(clip);
            txt_copy.setText("Copied");
        } else {
            Toast.makeText(getActivity(), "Link Not Available", Toast.LENGTH_SHORT).show();
        }
    }
}
