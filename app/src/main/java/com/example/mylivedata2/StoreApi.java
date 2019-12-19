package com.example.mylivedata2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StoreApi {
    @GET("storeOffers/")
    Call<StoreInfo> getStoreInfo();
}
