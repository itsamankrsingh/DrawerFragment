package com.example.drawerfragment.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.drawerfragment.Fragment.HomeFragment;
import com.example.drawerfragment.Fragment.ProfileFragment;
import com.example.drawerfragment.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.onFragmentBtnSelected {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        openHome();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        if (menuItem.getItemId() == R.id.menuHome) {
            openHome();

            drawerLayout.closeDrawers();
        }

        if (menuItem.getItemId() == R.id.menuHistory) {
            Toast.makeText(this, "History Menu Selected", Toast.LENGTH_SHORT).show();
        }

        if (menuItem.getItemId() == R.id.menuProfile) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameContentMain, new ProfileFragment());
            //fragmentTransaction.addToBackStack("Profile");
            fragmentTransaction.commit();
            drawerLayout.closeDrawers();
        }

        if (menuItem.getItemId() == R.id.menuAbout) {
            Toast.makeText(this, "About Menu Selected", Toast.LENGTH_SHORT).show();
        }


        return true;
    }

    @Override
    public void onButtonSelected() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContentMain, new ProfileFragment());
        fragmentTransaction.commit();
        drawerLayout.closeDrawers();
    }

    public void openHome() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContentMain, new HomeFragment());
        //fragmentTransaction.addToBackStack("Home");
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");
        navigationView.setCheckedItem(R.id.menuHome);
    }

  /* @Override
    public void onBackPressed() {
        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.frameContentMain);

        if (frag != new HomeFragment()) {
            openHome();
        }else {
            Toast.makeText(this,"Back Pressed",Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }*/

}