package com.adefitri.posyandu.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseURL = "https://posyandunusaindahbjm.xyz/posyandu_ns/";
    //private static final String baseURL = "https://posyandunusaindahbjm.000webhostapp.com/posyandu_ns/";
    //private static final String baseURL = "http://192.168.43.62/mposyandu_ns/";

    private static Retrofit retro;
    public static Retrofit konekRetrofit(){
        if (retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retro;
    }
}

