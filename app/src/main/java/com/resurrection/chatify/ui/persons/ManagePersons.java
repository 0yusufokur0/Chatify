package com.resurrection.chatify.ui.persons;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.resurrection.chatify.MainActivity;
import com.resurrection.chatify.R;
import com.resurrection.chatify.ui.CustomViewPager;

public class ManagePersons extends AppCompatActivity {

    private CustomViewPager customViewPager;
    private ViewPager viewPager;

    private void init(){
        customViewPager = new CustomViewPager(getSupportFragmentManager());
/*
        viewPager = findViewById(R.id.managePersonViewPager);
*/
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_persons);
        init();
        actionBar("Select Contact", "get contact count");
/*
        customViewPager.addFragment(new ChoosePersonFragment(),"ChoosePersonFragment");
        customViewPager.addFragment(new CreatePersonFragment(),"CreatePersonFragment");
        viewPager.setAdapter(customViewPager);
        viewPager.setCurrentItem(0);
*/
        ManagePersons.this.getSupportFragmentManager().beginTransaction().replace(R.id.manageFrameLayout, new ChoosePersonFragment()).addToBackStack(null).commit();




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.persons_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.newContact:
                // change fragment
                actionBar("Create Contact", "null");
                ManagePersons.this.getSupportFragmentManager().beginTransaction().replace(R.id.manageFrameLayout, new CreatePersonFragment()).addToBackStack(null).commit();
/*
                viewPager.setCurrentItem(1);
*/

                return true;

            case R.id.newGroup:
                // change fragment
                return true;

            case R.id.inviteAFriend:
                // change fragment
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void actionBar(String title, String subtitle) {
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setTitle(title);
        if (!subtitle.equals("null")) {
            actionBar.setSubtitle(subtitle);
        }

    }



}