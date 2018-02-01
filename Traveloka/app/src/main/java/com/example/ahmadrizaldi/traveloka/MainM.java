package com.example.ahmadrizaldi.traveloka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainM extends AppCompatActivity {

//    private TextView mTextMessage;






    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, homeFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:

                    SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    String emaill = sharedPref.getString("username","");
                    BookingFragment homeFragments = new BookingFragment();
                    homeFragments.setEmail(emaill);
                    FragmentTransaction fragmentTransactions = getSupportFragmentManager().beginTransaction();
                    fragmentTransactions.replace(R.id.content, homeFragments);
                    fragmentTransactions.commit();
                    return true;
                case R.id.navigation_notifications:
                    UserFragment homeFragmentss = new UserFragment();
                    SharedPreferences sharedPref2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    String emaill2 = sharedPref2.getString("username","");
                    String poinn = sharedPref2.getString("poin","");

                    homeFragmentss.setData(emaill2, poinn );
                    FragmentTransaction fragmentTransactionss = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionss.replace(R.id.content, homeFragmentss);
                    fragmentTransactionss.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, homeFragment);
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void onBackPressed() {
        moveTaskToBack(false);
    }

}
