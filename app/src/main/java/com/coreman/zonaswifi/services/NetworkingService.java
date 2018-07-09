package com.coreman.zonaswifi.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.coreman.zonaswifi.models.ZonaWifiItem;

import java.io.IOException;

import retrofit2.Call;

public class NetworkingService extends IntentService {

    public static final String TAG = "NetworkingService";
    public static final String SEARCH_QUERY = "searchQuery";
    public static final String SERVICE_MESSAGE = "serviceMessage";
    public static final String SERVICE_PAYLOAD = "servicePayload";

    public NetworkingService() {
        super("NetworkingService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent.hasExtra(SEARCH_QUERY)) {
            String searchParameter = intent.getStringExtra(SEARCH_QUERY);

            DataEndPointInterface endPointInterface = DataEndPointInterface.retrofit.create(DataEndPointInterface.class);

            Call<ZonaWifiItem[]> mZonaItem = endPointInterface.zonaWifiItems(searchParameter);

            ZonaWifiItem[] zonaWifiItems;

            try {
                zonaWifiItems = mZonaItem.execute().body();

                if (zonaWifiItems.length != 0) {
                    sendDataFromServer(zonaWifiItems);
                } else {
                    mZonaItem = endPointInterface.zonaWifiItemsMunicipio(searchParameter);
                    zonaWifiItems = mZonaItem.execute().body();

                    sendDataFromServer(zonaWifiItems);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private void sendDataFromServer(ZonaWifiItem[] zonaWifiItems) {
        Intent sendDataFromServer = new Intent(SERVICE_MESSAGE);
        sendDataFromServer.putExtra(SERVICE_PAYLOAD, zonaWifiItems);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(sendDataFromServer);
    }
}
