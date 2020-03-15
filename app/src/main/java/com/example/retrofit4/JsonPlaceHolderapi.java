package com.example.retrofit4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderapi {

    @GET("users")
    Call<List<Users>> getUsers();
}
