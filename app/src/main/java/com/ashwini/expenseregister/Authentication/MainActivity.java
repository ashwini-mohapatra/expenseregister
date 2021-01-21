package com.ashwini.expenseregister.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ashwini.expenseregister.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().hasExtra("extra")){
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, new LoginFragment()).commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, new SplashFragment()).commit();
        }
    }
}