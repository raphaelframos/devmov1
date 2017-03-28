package com.powellapps.g1fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.powellapps.g1fragment.fragments.BotafogoFragment;
import com.powellapps.g1fragment.fragments.FlamengoFragment;
import com.powellapps.g1fragment.fragments.TimesFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    TimesFragment timesFragment = new TimesFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, timesFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    BotafogoFragment botafogoFragment = new BotafogoFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, botafogoFragment).commit();
                    return true;
                case R.id.navigation_notifications:
                    FlamengoFragment flamengoFragment = new FlamengoFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, flamengoFragment).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
