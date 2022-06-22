package com.acharyamukti.api;


import com.acharyamukti.model.DataModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @FormUrlEncoded

    @POST("clientapi/register1.php?apicall=signup")
    Call<DataModel> register(

            @Field("fname") String firstname,
            @Field("lname") String lastname,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile
    );

    @FormUrlEncoded
    @POST("clientapi/register1.php?apicall=login")
    Call<DataModel> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("appapi/send-api.php")
    Call<DataModel> getOTP(
            @Field("mobile") String mobile
    );

    @FormUrlEncoded
    @POST("appapi/verify_otp.php")
    Call<DataModel> verifyOTP(
            @Field("otp") String otp,
            @Field("mobile") String mobile

    );
}
