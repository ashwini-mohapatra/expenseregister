package com.ashwini.expenseregister.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashwini.expenseregister.R;
import com.github.mikephil.charting.charts.BarChart;

public class GraphFragment extends Fragment {

    private BarChart chart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_graph, container, false);
        chart=v.findViewById(R.id.chart1);
        return v;
    }
}