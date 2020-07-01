package com.imuons.globalfunds.fragment;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.imuons.globalfunds.R;
import com.imuons.globalfunds.adapter.PackageItemAdapter;
import com.imuons.globalfunds.responseModel.GetAddressDataModel;
import com.imuons.globalfunds.responseModel.GetAddressResponse;
import com.imuons.globalfunds.responseModel.PckageDataModel;
import com.imuons.globalfunds.responseModel.PckageResponseModel;
import com.imuons.globalfunds.retrofit.AppService;
import com.imuons.globalfunds.retrofit.ServiceGenerator;
import com.imuons.globalfunds.utils.AppCommon;
import com.imuons.globalfunds.utils.ViewUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeNewPaymentFragment extends Fragment {

    public boolean is_payment_dialog_open;
    @BindView(R.id.dialogBox)
    public RelativeLayout dialogBox;
    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;
    @BindView(R.id.comingvalue)
    TextView comingvalue;
    @BindView(R.id.amt)
    TextView amt;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.remainingamt)
    TextView remainingamt;
    @BindView(R.id.tvCurencyType)
    TextView tvCurencyType;
    @BindView(R.id.tvPaidAmount)
    TextView tvPaidAmount;
    @BindView(R.id.tvPaidCurrencyType)
    TextView tvPaidCurrencyType;
    @BindView(R.id.qrcode)
    ImageView qrcode;
    @BindView(R.id.copyto)
    Button copyto;
    @BindView(R.id.link_address)
    TextView link_address;
    List<PckageDataModel> dataModelList = new ArrayList<>();
    private View view;
    private PackageItemAdapter packageItemAdapter;
    private int finalvalue;
    private String straddress;

    public MakeNewPaymentFragment() {
        // Required empty public constructor
    }

    public static MakeNewPaymentFragment newInstance() {
        MakeNewPaymentFragment fragment = new MakeNewPaymentFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_make_new_payment, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {


        recycle_view.setHasFixedSize(true);
        recycle_view.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        packageItemAdapter = new PackageItemAdapter(getActivity(), MakeNewPaymentFragment.this, dataModelList);
        recycle_view.setAdapter(packageItemAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPackage();
    }

    public void getPackage() {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(getActivity());
            Call call = apiService.GetPackage();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    dialog.dismiss();
                    PckageResponseModel authResponse = (PckageResponseModel) response.body();
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

    private void setData(List<PckageDataModel> data) {
        dataModelList = data;
        packageItemAdapter.updateList(dataModelList);
    }

    public void getAddress(String amount, Integer id, String type) {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(getActivity());
            Map<String, Object> param = new HashMap<>();
            param.put("currency_code", type);
            param.put("hash_unit", amount);
            param.put("product_id", id);
            Call call = apiService.GetAddress(param);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    dialog.dismiss();
                    GetAddressResponse authResponse = (GetAddressResponse) response.body();
                    if (authResponse != null) {
                        Log.i("packgae list::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            is_payment_dialog_open = true;
                            dialogBox.setVisibility(View.VISIBLE);
                            setAddress(authResponse.getData());
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
                 //   Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAddress(GetAddressDataModel data) {
        comingvalue.setText("Amount $" + data.getPriceInUsd());

        amt.setText("Amount " + data.getPriceInUsd());
        tvPaidAmount.setText(String.valueOf(data.getReceivedAmount()));
        if (data.getNetworkType().equals("BTC")) {
            price.setText("( " + String.format("%.6f", data.getPriceInCurrency()) + " BTC )");
        }

        BigDecimal bd = new BigDecimal(data.getPriceInCurrency());
        BigDecimal bd2 = new BigDecimal(data.getReceivedAmount());

        BigDecimal remainingint = bd.subtract(bd2).setScale(8, BigDecimal.ROUND_HALF_UP);

        BigDecimal value0 = new BigDecimal(0);
        finalvalue = remainingint.compareTo(value0);
        if (finalvalue <= 0) {
            remainingamt.setText("0");

        } else {
            remainingamt.setText(remainingint.toPlainString());
        }
        link_address.setText(data.getAddress());
        straddress = data.getAddress();
        String newlink;
        if (data.getNetworkType().equals("BTC")) {
            newlink = "bitcoin:" + data.getAddress() + "?amount=" + String.format("%.6f", data.getPriceInCurrency());
            setQr(newlink);
        }
    }

    private void setQr(String newlink) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(newlink, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            qrcode.setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.closeBtn)
    public void close() {
        if (is_payment_dialog_open) {
            dialogBox.setVisibility(View.GONE);
            is_payment_dialog_open = false;
        }
    }

    @OnClick(R.id.copyto)
    public void copy() {
        if (straddress != null) {
            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", straddress);
            clipboard.setPrimaryClip(clip);
            copyto.setText("Copied");

        }
    }

}
