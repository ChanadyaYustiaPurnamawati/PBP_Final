package com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("customer")
    Call<UserResponse> getAllUser(@Query("data") String data);

    //mendapatkan user by id
    @GET("customer/{id}")
    Call<UserResponse> getUserById(@Path("id") String id,
                                   @Query("data") String data);

    //tambahan code untuk login, delete, update

    @POST("customer/delete/{id}")
    Call<UserResponse> deleteUser(@Path("id") String id);

    @POST("customer/update/{id}")
    @FormUrlEncoded
    Call<UserResponse> updateUser(@Path("id") String id, @Field("nama") String nama, @Field("alamat") String alamat,
                                  @Field("no_telp") String no_telp, @Field("no_ktp") String no_ktp,
                                  @Field("motor") String motor, @Field("waktu") int waktu);


    //mencreate user baru
    @POST("customer")
    @FormUrlEncoded
    Call<UserResponse> createUser(@Field("nama") String nama,
                                  @Field("alamat") String alamat,
                                  @Field("no_telp") String no_telp,
                                  @Field("no_ktp") String no_ktp,
                                  @Field("motor") String motor,
                                  @Field("waktu") String waktu);
}
