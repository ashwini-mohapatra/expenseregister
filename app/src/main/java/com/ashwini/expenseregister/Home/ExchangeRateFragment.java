package com.ashwini.expenseregister.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwini.expenseregister.Model.ExchangeRateModel.Exchange;
import com.ashwini.expenseregister.Model.ExchangeRateModel.ExchangeRateModel;
import com.ashwini.expenseregister.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExchangeRateFragment extends Fragment {

    Retrofit retrofit;
    Exchange exchange;

    EditText e1;
    Spinner s1,s2;
    TextView t1;
    Button b1;

    String spin1,spin2;
    ExchangeRateModel erm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_exchange_rate, container, false);

        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        List<String> items=new ArrayList<String>();

        items.add("INR");
        items.add("CAD");
        items.add("HDK");
        items.add("ISK");
        items.add("PHP");
        items.add("DKK");
        items.add("HUF");
        items.add("CZK");
        items.add("AUD");
        items.add("RON");
        items.add("SEK");
        items.add("IDR");
        items.add("BRL");
        items.add("RUB");
        items.add("HRK");
        items.add("JPY");
        items.add("THB");
        items.add("CHF");
        items.add("SGD");
        items.add("PLN");
        items.add("BGN");
        items.add("TRY");
        items.add("CNY");
        items.add("NOK");
        items.add("NZD");
        items.add("ZAR");
        items.add("USD");
        items.add("MXN");
        items.add("ILS");
        items.add("GBP");
        items.add("KRW");
        items.add("MYR");

        e1=v.findViewById(R.id.editTextPhone);
        s1=v.findViewById(R.id.spinner);
        s2=v.findViewById(R.id.spinner1);
        t1=v.findViewById(R.id.editTextPhone1);
        b1=v.findViewById(R.id.button);
        b1.setEnabled(false);

        ArrayAdapter<CharSequence> arrayAdapter1=ArrayAdapter.createFromResource(getActivity(),R.array.ExchangeRateArray, android.R.layout.simple_spinner_item);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(arrayAdapter1);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin1=parent.getItemAtPosition(position).toString();
                s1.setSelection(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s1.setSelection(0);
            }
        });

        ArrayAdapter<CharSequence> arrayAdapter2=ArrayAdapter.createFromResource(getActivity(),R.array.ExchangeRateArray, android.R.layout.simple_spinner_item);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(arrayAdapter2);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin2=parent.getItemAtPosition(position).toString();
                s2.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s2.setSelection(0);
            }
        });

        String link="https://api.exchangeratesapi.io/";
        retrofit=new Retrofit.Builder().baseUrl(link).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        exchange=retrofit.create(Exchange.class);
        Call<ExchangeRateModel> exchangeRateModelCall=exchange.getExchangeRate();
        exchangeRateModelCall.enqueue(new Callback<ExchangeRateModel>() {
            @Override
            public void onResponse(Call<ExchangeRateModel> call, Response<ExchangeRateModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(),"Response Successful",Toast.LENGTH_LONG).show();
                    erm=response.body();
                    b1.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<ExchangeRateModel> call, Throwable t) {
                    Toast.makeText(getActivity(),"Response Unsuccessful",Toast.LENGTH_LONG).show();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=0;
                if(e1.getText().toString().equals("")){
                    x=0;
                }else{
                    x=Integer.parseInt(e1.getText().toString());
                }
                HashMap<String,Double> hsd=erm.getRates();
                double x1=0.0,x2=0.0,x3=0.0;
                if(!spin1.equals("EUR") && !spin2.equals("EUR")){

                    x1=hsd.get(spin1);
                    x2=hsd.get(spin2);
                    x3=x*(x2/x1);
                }else if(!spin1.equals("EUR") && spin2.equals("EUR")){

                    x1=hsd.get(spin1);
                    x3=x/x1;

                }else if(spin1.equals("EUR") && !spin2.equals("EUR")){

                    x2=hsd.get(spin2);
                    x3=x*x2;
                }else{

                    x1=1.0;
                    x2=1.0;
                    x3=x*(x2/x1);
                }
                t1.setText(String.format("%.3f",x3));
            }
        });
        return v;
    }
}