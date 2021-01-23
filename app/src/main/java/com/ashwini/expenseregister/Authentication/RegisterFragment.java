package com.ashwini.expenseregister.Authentication;

import android.content.Intent;
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
import com.ashwini.expenseregister.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment {

    EditText e1,e2,e3;
    Button b1;
    TextView t1;

    FirebaseAuth mAuth;
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

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Status", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
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