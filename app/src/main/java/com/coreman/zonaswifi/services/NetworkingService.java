package com.coreman.zonaswifi.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.coreman.zonaswifi.models.ZonaWifiItem;
import com.coreman.zonaswifi.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;

public class NetworkingService extends IntentService {

    public static final String TAG = "NetworkingService";
    public static final String DEPARTMENT_QUERY = "departamento";
    public static final String SERVICE_MESSAGE = "serviceMessage";
    public static final String SERVICE_PAYLOAD = "servicePayload";

    public NetworkingService() {
        super("NetworkingService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent.hasExtra(DEPARTMENT_QUERY)) {
            String department = intent.getStringExtra(DEPARTMENT_QUERY);

            DataEndPointInterface endPointInterface = DataEndPointInterface.retrofit.create(DataEndPointInterface.class);
            Call<ZonaWifiItem[]> mZonaItem = endPointInterface.zonaWifiItems(department);

            ZonaWifiItem[] zonaWifiItems;

            try {
                zonaWifiItems = mZonaItem.execute().body();

                Intent sendDataFromServer = new Intent(SERVICE_MESSAGE);
                sendDataFromServer.putExtra(SERVICE_PAYLOAD, zonaWifiItems);
                LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
                manager.sendBroadcast(sendDataFromServer);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
