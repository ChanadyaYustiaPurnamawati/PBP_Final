package com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //untuk koneksi ke url
    public static final String BASE_URL = "http://hokirental.xyz/api/";
    public static Retrofit retrofit = null;
    public static Retrofit getClient()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
