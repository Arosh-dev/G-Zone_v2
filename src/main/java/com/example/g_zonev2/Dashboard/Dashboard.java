package com.example.g_zonev2.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.g_zonev2.AR.AR;
import com.example.g_zonev2.Profile.Profile;
import com.example.g_zonev2.R;
import com.example.g_zonev2.Tasks.Tasks;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Dashboard extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        chipNavigationBar = findViewById(R.id.bottom_nav_menue);

        bottomMenue();
    }

    private void bottomMenue() {

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                Intent in = getIntent();
                switch (i){
                    case R.id.botom_nav_home:
                        fragment = new DashboardFragment();
                        break;
                    case R.id.botom_nav_profile:
                        fragment = new Profile(in.getStringExtra("name"));
                        break;
                    case R.id.botom_nav_task:
                        fragment = new Tasks();
                        break;
                    case R.id.botom_nav_camera:
                        fragment = new AR();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
    }
}