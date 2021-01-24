package com.ashwini.expenseregister.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwini.expenseregister.Authentication.MainActivity;
import com.ashwini.expenseregister.Model.Profile.Profile;
import com.ashwini.expenseregister.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    ImageView i1;

    AdView mAdView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    FirebaseDatabase mDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        mDatabase=FirebaseDatabase.getInstance();
        databaseReference=mDatabase.getReference();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.homeframe,new HomeFragment()).commit();

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        sharedPreferences=getSharedPreferences("Expense Register",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.navigation_menu);

        setSupportActionBar(toolbar);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        View header=navigationView.getHeaderView(0);
        TextView t1=header.findViewById(R.id.textView3);

        String uid=mAuth.getUid();
        databaseReference.child("Expense Register").child(uid).child("Profile");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("Data Status","Data Update Successful");
                Profile profile=snapshot.getValue(Profile.class);
                Log.i("Data",profile.getName());
                t1.setText(profile.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Data Status","Data Update Cancelled");
            }
        });


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
                        mAuth.signOut();
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
}