package com.imuons.globalfunds.view;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.adapter.CustomExpandableListAdapter;
import com.imuons.globalfunds.dataModel.ExpandableListModel;
import com.imuons.globalfunds.fragment.ChangePasswordFragment;
import com.imuons.globalfunds.fragment.ConfirmedPaymentsFragment;
import com.imuons.globalfunds.fragment.DirectUserListFragment;
import com.imuons.globalfunds.fragment.EditProfileFragment;
import com.imuons.globalfunds.fragment.HomeFragment;
import com.imuons.globalfunds.fragment.LevelViewFragment;
import com.imuons.globalfunds.fragment.MakeNewPaymentFragment;
import com.imuons.globalfunds.fragment.OnGoingPayments;
import com.imuons.globalfunds.fragment.ProfileFragment;
import com.imuons.globalfunds.utils.AppCommon;
import com.imuons.globalfunds.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    ActionBarDrawerToggle mDrawerToggle;
    boolean is_home = true;
    private ExpandableListAdapter mExpandableListAdapter;
    private ArrayList<ExpandableListModel> mExpandableListTitle;
    private HashMap<String, ArrayList<String>> mExpandableListData;
    private DrawerLayout mDrawerLayout;
    private ExpandableListView mExpandableListView;
    private FragmentManager fragmentManager;
    private Toolbar toolbar;
    private MakeNewPaymentFragment makePayment;
    private int child_itme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        TextView user_id = toolbar.findViewById(R.id.tv_user);
        user_id.setText(AppCommon.getInstance(this).getUserId());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title_activity_dashboard);
        }
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mExpandableListView = findViewById(R.id.navList);
        mExpandableListView.setIndicatorBounds(mExpandableListView.getRight() + 120, mExpandableListView.getWidth());
        setupToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        prepareListData();

       firstFragment();
        setupDrawerToggle();

        Listitemclick();

    }

    private void firstFragment() {
        is_home=true;
        fragmentManager = getSupportFragmentManager();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        fragmentManager.beginTransaction().replace(R.id.content_frame, HomeFragment.newInstance("", "")).commit();
    }

    private void Listitemclick() {
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);

                return true;
            }
        });
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {


                switch (groupPosition) {
                    case 0:
                        is_home = true;
                        fragmentManager.beginTransaction().replace(R.id.content_frame, HomeFragment.newInstance("", "")).commit();
                        getSupportActionBar().setTitle("Dashboard");
                        mDrawerLayout.closeDrawers();
                        break;

                    case 6:
                        is_home = true;
                        showAlertDialog();
                        break;
                }
                return false;
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                switch (groupPosition) {

                    case 1:
                        switch (childPosition) {

                            case 0:
                                is_home = false;
                                child_itme = childPosition;
                                fragmentManager.beginTransaction().replace(R.id.content_frame, ProfileFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Profile");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 1:
                                is_home = false;
                                child_itme = childPosition;
                                fragmentManager.beginTransaction().replace(R.id.content_frame, EditProfileFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Edit Profile");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:
                                is_home = false;
                                child_itme = childPosition;
                                fragmentManager.beginTransaction().replace(R.id.content_frame, ChangePasswordFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Change Password");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                        }
                        break;

                    case 2:
                        switch (childPosition) {
                            case 0:
                                is_home = false;
                                child_itme = childPosition;
                                makePayment = MakeNewPaymentFragment.newInstance();
                                fragmentManager.beginTransaction().replace(R.id.content_frame, makePayment).commit();
                                getSupportActionBar().setTitle("Make New Payment");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                is_home = false;
                                child_itme = childPosition;
                                fragmentManager.beginTransaction().replace(R.id.content_frame, OnGoingPayments.newInstance()).commit();
                                getSupportActionBar().setTitle("On Going Payment");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:
                                is_home = false;
                                child_itme = childPosition;
                                fragmentManager.beginTransaction().replace(R.id.content_frame, ConfirmedPaymentsFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Confirm Payment");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;
                    case 3:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DirectUserListFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Direct User List");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, LevelViewFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Level View");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;


                }
                mDrawerLayout.closeDrawers();
                return false;

            }
        });

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < mExpandableListView.getCount(); i++) {
                    if (i != groupPosition) {
                        mExpandableListView.collapseGroup(i);
                    }
                }
            }
        });
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    private void prepareListData() {
        mExpandableListTitle = new ArrayList<ExpandableListModel>();
        mExpandableListData = new HashMap<String, ArrayList<String>>();

        mExpandableListTitle.add(new ExpandableListModel(R.drawable.dashboard, "Dashboard"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.userss, "Profile"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.money, "Deposit"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.people, "My Team"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.report, "Income Report"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.withdraw, "Withdrawal"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.logout, "Logout"));

        //two
        ArrayList<String> profileList = new ArrayList<String>();
        profileList.add("My Profile");
        profileList.add("Edit Profile");
        profileList.add("Change Password");


        //three
        ArrayList<String> depostiList = new ArrayList<String>();
        depostiList.add("Make New Payment");
        depostiList.add("Ongoing Payment");
        depostiList.add("Confirmed Payments");

        //four
        ArrayList<String> myTemaList = new ArrayList<String>();
        myTemaList.add("Direct User List");
        myTemaList.add("Level View");


        //five
        ArrayList<String> incomereport = new ArrayList<String>();
        incomereport.add("ROI Income Report");
        incomereport.add("Level Income Report");
        incomereport.add("Level ROI Income Report");
        incomereport.add("Award Income Report");

        //six
        ArrayList<String> withdrawal = new ArrayList<String>();
        withdrawal.add("Make Working Withdrawal");
        withdrawal.add("Confirmed Withdrawal");
        withdrawal.add("Ongoing Withdrawal");


        ArrayList<String> allTransactions = new ArrayList<String>();
        mExpandableListData.put(mExpandableListTitle.get(0).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(1).title, profileList);
        mExpandableListData.put(mExpandableListTitle.get(2).title, depostiList);
        mExpandableListData.put(mExpandableListTitle.get(3).title, myTemaList);
        mExpandableListData.put(mExpandableListTitle.get(4).title, incomereport);
        mExpandableListData.put(mExpandableListTitle.get(5).title, withdrawal);
        mExpandableListData.put(mExpandableListTitle.get(6).title, allTransactions);


        mExpandableListAdapter = new CustomExpandableListAdapter(HomeActivity.this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);

    }

    private void showAlertDialog() {
       // AlertDialog.Builder builder1 = null;
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            builder1 = new AlertDialog.Builder(HomeActivity.this, AlertDialog.THEME_HOLO_LIGHT);
        }
        builder1.setTitle("Alert");
        builder1.setMessage("Are you sure you want to Logout ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                HomeActivity.this.finish();
                SharedPreferenceUtils.clearPreferences(HomeActivity.this);
                SharedPreferenceUtils.clearID(HomeActivity.this);
                SharedPreferenceUtils.clearAccess_Token(HomeActivity.this);
                SharedPreferenceUtils.storeSplash(HomeActivity.this, "stop");
                AppCommon.getInstance(HomeActivity.this).clearPreference();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finishAffinity();
                Toast.makeText(HomeActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert11 = builder1.create();
        alert11.show();*/




            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(getResources().getString(R.string.app_name));
            adb.setIcon(R.mipmap.ic_launcher_round);
            adb.setMessage("Are you sure you want to Logout ?");
            adb.setPositiveButton(getResources().getString(R.string.yes),
                    new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AppCommon.getInstance(HomeActivity.this).clearPreference();
                            AppCommon.getInstance(getApplicationContext()).setUserLogin(AppCommon.getInstance(getApplicationContext()).getUserId(), false);
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                            finishAffinity();
                            Toast.makeText(HomeActivity.this, getString(R.string.logout_success),
                                    Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent());
                            // finishAffinity();
                        }

                    });
            adb.setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            adb.show();



    }

    @Override
    public void onBackPressed() {
        if(is_home){
            finish();
        }else{
           firstFragment();
        }
        if (makePayment != null) {
            if (makePayment.is_payment_dialog_open) {
                makePayment.dialogBox.setVisibility(View.GONE);
            } else {

            }
        }

    }
}
