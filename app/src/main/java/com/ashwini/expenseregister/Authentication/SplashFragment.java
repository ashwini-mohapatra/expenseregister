package com.ashwini.expenseregister.Authentication;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ashwini.expenseregister.Home.HomeActivity;
import com.ashwini.expenseregister.R;

public class SplashFragment extends Fragment {

    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_splash, container, false);

        imageView = v.findViewById(R.id.imageView);
        imageView.setAlpha(0f);

        imageView.animate().alpha(1f).setDuration(3000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Expense Register", Context.MODE_PRIVATE);
                if (sharedPreferences.getInt("status", 0) == 1) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,new LoginFragment()).commit();
                } else {
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().finish();
                }
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        return v;
    }
}