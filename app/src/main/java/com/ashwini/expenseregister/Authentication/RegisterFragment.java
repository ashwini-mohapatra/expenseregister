package com.ashwini.expenseregister.Authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwini.expenseregister.Home.HomeActivity;
import com.ashwini.expenseregister.Home.HomeFragment;
import com.ashwini.expenseregister.Model.Profile.Profile;
import com.ashwini.expenseregister.Model.TotalTransactionsModel.TotalModel;
import com.ashwini.expenseregister.R;
import com.eyalbira.loadingdots.LoadingDots;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterFragment extends Fragment {

    EditText e1,e2,e3;
    Button b1;
    TextView t1;
    LoadingDots ld;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseDatabase database1 = FirebaseDatabase.getInstance();
    DatabaseReference myRef1 = database1.getReference();

    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_register, container, false);

        e1=v.findViewById(R.id.registername);
        e2=v.findViewById(R.id.registeremail);
        e3=v.findViewById(R.id.registerpassword);
        t1=v.findViewById(R.id.register_sign_in);
        b1=v.findViewById(R.id.registerbtn);
        ld=v.findViewById(R.id.ld);

        sharedPreferences=getActivity().getSharedPreferences("Expense Register", Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ld.setVisibility(View.VISIBLE);
                b1.setVisibility(View.GONE);
                String x1=e1.getText().toString();
                String x2=e2.getText().toString();
                String x3=e3.getText().toString();
                mAuth.createUserWithEmailAndPassword(x2,x3).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Status", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user!=null){
                                String uid=user.getUid();
                                String name=x1;
                                String email=user.getEmail();
                                Profile profile=new Profile(uid,name,email);
                                myRef.child("Expense Register").child(uid).child("Profile").setValue(profile);
                                editor=sharedPreferences.edit();
                                editor.putInt("status",1);
                                editor.apply();
                                startActivity(new Intent(getActivity(),HomeActivity.class));
                            }
                        } else {
                            ld.setVisibility(View.GONE);
                            b1.setVisibility(View.VISIBLE);
                            // If sign in fails, display a message to the user.
                            Log.w("Status", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed- "+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,new LoginFragment()).commit();
            }
        });
        return v;
    }
}