package com.acharyamukti.api;


import com.acharyamukti.model.DataModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded

    @POST("register1.php")
    Call<DataModel> register(

            @Field("fname") String firstname,
            @Field("lname") String lastname,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile
    );

    @GET("send-api.php")
    Call<DataModel> login(
            @Field("mobile") String mobile
    );
}
