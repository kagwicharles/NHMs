package com.kagwisoftwares.nhms;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kagwisoftwares.nhms.ui.Staff.StaffFragment;
import com.kagwisoftwares.nhms.ui.analytics.AnalyticsFragment;
import com.kagwisoftwares.nhms.ui.dashboard.DashboardFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new DashboardFragment()).commit();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment selected = new Fragment();
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selected = new DashboardFragment();
                        break;

                    case R.id.navigation_facility:
                       selected = new AnalyticsFragment();
                       break;
                    case R.id.navigation_staff:
                        selected = new StaffFragment();
                        break;
                }
                ft.replace(R.id.frameLayout, selected).commit();
                return true;
            }
        });
    }

}