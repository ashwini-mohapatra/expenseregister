package com.ashwini.expenseregister.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwini.expenseregister.Authentication.MainActivity;
import com.ashwini.expenseregister.R;
import com.google.android.material.navigation.NavigationView;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements PaymentResultListener {

    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    ImageView i1;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Checkout.preload(getApplicationContext());
        i1=findViewById(R.id.imageView2);
        sharedPreferences=getSharedPreferences("Expense Register",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        if(sharedPreferences.getInt("paid",0)==1){
            i1.setVisibility(View.GONE);
        }else{
            i1.setVisibility(View.VISIBLE);
        }

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.navigation_menu);

        setSupportActionBar(toolbar);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        View header=navigationView.getHeaderView(0);
        TextView t1=header.findViewById(R.id.textView3);
        t1.setText("Ashwini Mohapatra");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.homeframe,new HomeFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.graph:
                        getSupportFragmentManager().beginTransaction().replace(R.id.homeframe,new GraphFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.exchangerate:
                        getSupportFragmentManager().beginTransaction().replace(R.id.homeframe,new ExchangeRateFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.privacypolicy:
                        getSupportFragmentManager().beginTransaction().replace(R.id.homeframe,new PrivacyPolicyFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.aboutus:
                        getSupportFragmentManager().beginTransaction().replace(R.id.homeframe,new AboutUsFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.contactus:
                        getSupportFragmentManager().beginTransaction().replace(R.id.homeframe,new ContactUsFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.terms:
                        getSupportFragmentManager().beginTransaction().replace(R.id.homeframe,new TermsConditionsFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        editor.putInt("status",0);
                        editor.apply();
                        Intent intent=new Intent(HomeActivity.this, MainActivity.class);
                        intent.putExtra("extra",1);
                        startActivity(intent);
                        HomeActivity.this.finish();
                        break;
                }
                return true;
            }
        });
    }
    void startPayment(){
        Checkout checkout=new Checkout();
        checkout.setKeyID("rzp_test_fcwS67f3uOsXRC");
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("name","Expense Register");
            jsonObject.put("description","Inapp Purchase for Extra Features");
            jsonObject.put("currency","INR");
            jsonObject.put("amount","49900");
            JSONObject prefill=new JSONObject();
            prefill.put("contact","7427829367");
            prefill.put("email","ashwini152066@gmail.com");
            prefill.put("name","Ashwini Mohapatra");
            jsonObject.put("prefill",prefill);
            jsonObject.put("allow_rotation",true);
            checkout.open(HomeActivity.this,jsonObject);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(HomeActivity.this,"Successful",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(HomeActivity.this,"UnSuccessful",Toast.LENGTH_LONG).show();
    }

}