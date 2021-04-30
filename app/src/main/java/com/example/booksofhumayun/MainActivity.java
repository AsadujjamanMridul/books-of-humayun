package com.example.booksofhumayun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.booksofhumayun.fragment.AuthorFragment;
import com.example.booksofhumayun.fragment.DevFragment;
import com.example.booksofhumayun.fragment.HomeFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FrameLayout frameLayout;
    ChipNavigationBar chipNavigationBar;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        frameLayout = findViewById(R.id.frameLayout);
        fragmentManager = getSupportFragmentManager();
        chipNavigationBar = findViewById(R.id.navigation);


        if(savedInstanceState==null)
        {
            chipNavigationBar.setItemSelected(R.id.nav_home,true);
            HomeFragment frag1 = new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.frameLayout,frag1).commit();
        }

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                Fragment fragment = null;

                switch (id) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.author:
                        fragment = new AuthorFragment();
                        break;

                    case R.id.dev:
                        fragment = new DevFragment();
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + id);
                }

                if (fragment != null) {
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                }

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}
