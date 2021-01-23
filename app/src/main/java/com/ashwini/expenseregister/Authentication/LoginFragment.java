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
import com.ashwini.expenseregister.R;
import com.eyalbira.loadingdots.LoadingDots;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.view.View.GONE;

public class LoginFragment extends Fragment {

    EditText e1,e2;
    TextView t1;
    Button b1;
    LoadingDots ld;

    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_login, container, false);

        e1=v.findViewById(R.id.loginemail);
        e2=v.findViewById(R.id.loginpassword);
        t1=v.findViewById(R.id.login_sign_up);
        b1=v.findViewById(R.id.loginbtn);
        ld=v.findViewById(R.id.ld);

        sharedPreferences=getActivity().getSharedPreferences("Expense Register", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setVisibility(GONE);
                ld.setVisibility(View.VISIBLE);
                String x1=e1.getText().toString();
                String x2=e2.getText().toString();
                mAuth.signInWithEmailAndPassword(x1, x2)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Status", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if(user!=null){
                                        editor.putInt("status",1);
                                        editor.apply();
                                        startActivity(new Intent(getActivity(), HomeActivity.class));
                                    }
                                } else {
                                    b1.setVisibility(View.VISIBLE);
                                    ld.setVisibility(GONE);
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
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,new RegisterFragment()).commit();
            }
        });

        return v;
    }
}