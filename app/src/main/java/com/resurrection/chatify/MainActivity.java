package com.resurrection.chatify;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.resurrection.chatify.ui.CustomViewPager;
import com.resurrection.chatify.ui.main.calls.CallsFragment;
import com.resurrection.chatify.ui.main.chat.ChatFragment;
import com.resurrection.chatify.ui.main.status.StatusFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CustomViewPager customViewPager;
    private TabLayout tabLayout;

    private void init(){
        viewPager = findViewById(R.id.mainViewPager);
        customViewPager = new CustomViewPager(getSupportFragmentManager());
        tabLayout = findViewById(R.id.maintablayout);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setFragmentsAndTabLayout();



    }

    private void setFragmentsAndTabLayout(){
        customViewPager.addFragment(new CallsFragment(),"Calls");
        customViewPager.addFragment(new ChatFragment(),"Chat");
        customViewPager.addFragment(new StatusFragment(),"Status");
        viewPager.setAdapter(customViewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);
    }
    public boolean requestStoragePermission(Context context) {
        final int STORAGE_REQUEST_CODE = 101;
        String[] storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        boolean isPermissionGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        if (!isPermissionGranted) {
            ActivityCompat.requestPermissions((Activity) context, storagePermission, STORAGE_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

}