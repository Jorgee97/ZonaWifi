package com.coreman.zonaswifi.services;

import com.coreman.zonaswifi.models.ZonaWifiItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataEndPointInterface {
    String BASE_URL = "https://www.datos.gov.co/";
    String WHOLE = "resource/hbap-jitp.json";
    String DEPARTMENT = "resource/hbap-jitp.json?$order=municipio%20ASC";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET(WHOLE)
    Call<ZonaWifiItem[]> zonaWifiItems();

    @GET(DEPARTMENT)
    Call<ZonaWifiItem[]> zonaWifiItems(@Query("departamento") String departamento);
}