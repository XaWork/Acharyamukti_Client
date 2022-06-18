package com.acharyamukti.api;


import com.acharyamukti.model.DataModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @POST("clientapi/register1.php?apicall=login")
    Call<DataModel> login(
            @Field("email") String email,
            @Field("password") String password

    );
}
