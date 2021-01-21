package com.ashwini.expenseregister.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ashwini.expenseregister.Model.ExchangeRateModel.Exchange;
import com.ashwini.expenseregister.Model.ExchangeRateModel.ExchangeRateModel;
import com.ashwini.expenseregister.R;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_exchange_rate, container, false);

        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        String link="http://data.fixer.io/api/";
        retrofit=new Retrofit.Builder().baseUrl(link).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        exchange=retrofit.create(Exchange.class);
        Call<ExchangeRateModel> exchangeRateModelCall=exchange.getExchangeRate("766316eb5943eb92d9e840f7203574e1");
        exchangeRateModelCall.enqueue(new Callback<ExchangeRateModel>() {
            @Override
            public void onResponse(Call<ExchangeRateModel> call, Response<ExchangeRateModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(),"Response Successful",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ExchangeRateModel> call, Throwable t) {
                    Toast.makeText(getActivity(),"Response Unsuccessful",Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}