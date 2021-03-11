package com.kagwisoftwares.nhms;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
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
        ChipNavigationBar navView = findViewById(R.id.nav_view);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new DashboardFragment()).commit();
        navView.setItemSelected(R.id.navigation_home,true);

        navView.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment selected = new Fragment();
                switch (i) {
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
            }
        });
    }
}