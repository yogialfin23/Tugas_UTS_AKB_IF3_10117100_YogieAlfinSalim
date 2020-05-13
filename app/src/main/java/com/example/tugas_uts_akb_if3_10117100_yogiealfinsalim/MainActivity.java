package com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.view.ContactActivity;
import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.view.ProfileActivity;
import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.view.listFriendActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/** NIM : 10117100
 * Nama : Yogie Alfin Salim
 * Kelas : IF-3
 * Tanggal : 12-05-2020
 **/

public class MainActivity  extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03AC0E")));

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new listFriendActivity()).commit();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fragmentManager = getSupportFragmentManager();
        listFriendActivity fragmentListFriend = new listFriendActivity();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentListFriend).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.friend:
                            selectedFragment = new listFriendActivity();
                            break;
                        case R.id.contact:
                            selectedFragment = new ContactActivity();
                            break;
                        case R.id.profile:
                            selectedFragment = new ProfileActivity();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

}