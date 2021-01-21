package com.ashwini.expenseregister.Model.ExchangeRateModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Exchange {

    @GET("latest")
    Call<ExchangeRateModel> getExchangeRate(@Query("access_key") String key);
}
