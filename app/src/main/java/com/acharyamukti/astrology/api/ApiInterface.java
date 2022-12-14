package com.acharyamukti.astrology.api;

import com.acharyamukti.astrology.model.AstroProfileModel;
import com.acharyamukti.astrology.model.BlogModel;
import com.acharyamukti.astrology.model.DataModel;
import com.acharyamukti.astrology.model.HoroscopeModel;
import com.acharyamukti.astrology.model.ImageModel;
import com.acharyamukti.astrology.model.UserProfileModel;
import com.acharyamukti.astrology.model.CallDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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

    @GET("clientapi/daily-horoscope.php")
    Call<List<ImageModel>> getHoroscope();

    @FormUrlEncoded
    @POST("clientapi/daily-horoscope-more.php")
    Call<HoroscopeModel> getBlog(
            @Field("horoscop_name") String title
    );

    @FormUrlEncoded
    @POST("clientapi/blog-details.php")
    Call<BlogModel> getBlogDetails(
            @Field("blog_id") String id
    );

    @FormUrlEncoded
    @POST("clientapi/feedback.php")
    Call<DataModel> postFeedBack(
            @Field("user_id") String userId,
            @Field("content") String content
    );

    @POST("clientapi/forgot-pass.php")
    Call<DataModel> postPasswordLink(
            @Field("email") String email
    );


    @Headers({
            "Content-Type: application/json",
            "Authorization: 800333b2-405d-4947-899f-f7686663d30f",
            "x-api-key: 6m9Ux0on1k1opZ1qyEZMr4cl29UfAPqK2rryZCZR"
    })
    @POST("Basic/v1/account/call/makecall")
    Call<CallDataModel> getCalling(@Body CallDataModel dataModel);

    @FormUrlEncoded
    @POST("clientapi/wallet.php")
    Call<DataModel> getTotalBalance(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("clientapi/pay.php")
    Call<DataModel> postPaymentDetails(
            @Field("user_id") String user_id,
            @Field("amount_credited") String acc_details,
            @Field("txnid") String txnid
    );

    @FormUrlEncoded
    @POST("clientapi/calling.php")
    Call<DataModel> getCallDurations(
            @Field("user_id") String userId,
            @Field("reg_id") String reg_id
    );

    @FormUrlEncoded
    @POST("/clientapi/profile.php")
    Call<DataModel> viewProfile(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("/clientapi/update-profile.php")
    Call<UserProfileModel> updateProfile(
            @Field("user_id") String userId,
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("email") String email,
            @Field("mobile") String mobile);

    @GET("clientapi/online-astro.php")
    Call<List<AstroProfileModel>> getProfileData();
}

