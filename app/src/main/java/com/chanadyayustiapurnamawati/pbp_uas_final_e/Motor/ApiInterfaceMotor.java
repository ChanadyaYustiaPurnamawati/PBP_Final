package com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterfaceMotor {
    @GET("motor")
    Call<MotorResponse> getAllUser(@Query("data") String data);

    //mendapatkan user by id
    @GET("motor/{id}")
    Call<MotorResponse> getUserById(@Path("id") String id,
                                   @Query("data") String data);

    //tambahan code untuk login, delete, update

    @POST("motor/delete/{id}")
    Call<MotorResponse> deleteUser(@Path("id") String id);

    @POST("motor/update/{id}")
    @FormUrlEncoded
    Call<MotorResponse> updateUser(@Path("id") String id, @Field("merk") String merk, @Field("kondisi") String kondisi,
                                  @Field("jenis") String jenis, @Field("harga") int harga,
                                  @Field("tahun") int tahun, @Field("foto") String foto);


    //mencreate user baru
    @POST("motor")
    @FormUrlEncoded
    Call<MotorResponse> createMotor(@Field("merk") String merk,
                                   @Field("kondisi") String kondisi,
                                   @Field("jenis") String jenis,
                                   @Field("harga") int harga,
                                   @Field("tahun") int tahun,
                                    @Field("foto") String foto);

}
