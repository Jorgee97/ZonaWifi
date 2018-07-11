package com.coreman.zonaswifi.ui.main;

import com.coreman.zonaswifi.services.DataEndPointInterface;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZonaWifiPresenter implements ZonaWifiContract.Presenter {

    private ZonaWifiContract.View mainView;
    private boolean checkNetworkConnection;

    DataEndPointInterface endPointInterface;

    public ZonaWifiPresenter(ZonaWifiContract.View mainView, boolean checkNetworkConnection) {
        this.mainView = mainView;
        this.checkNetworkConnection = checkNetworkConnection;

        endPointInterface = DataEndPointInterface.retrofit.create(DataEndPointInterface.class);
    }

    @Override
    public void doesItHaveInternet() {
        if (!checkNetworkConnection)
            mainView.showError("There is not internet connection, please connect to the internet to" +
                    "keep using the app.");
    }

    @Override
    public void requestWifiPoints(String searchParameter) {
        if (checkNetworkConnection && !searchParameter.equals("")) {
            mainView.showLoader();
            Call<ZonaWifiItem[]> mZonaItem = endPointInterface.zonaWifiItems(searchParameter);

            requestByDepartment(mZonaItem, searchParameter);
        }
        else {
            mainView.showError("Please full fill the search box, or validate your internet connection");
        }
    }

    public void requestByDepartment(final Call<ZonaWifiItem[]> mZonaItem, final String searchParameter) {
        mZonaItem.enqueue(new Callback<ZonaWifiItem[]>() {
            @Override
            public void onResponse(Call<ZonaWifiItem[]> call, Response<ZonaWifiItem[]> response) {
                if (response.isSuccessful() && response.body().length > 0) {
                    mainView.displayData(Arrays.asList(response.body()));
                    mainView.hideLoader();
                } else {
                    Call<ZonaWifiItem[]> mZonaMunicipio = endPointInterface.zonaWifiItemsMunicipio(searchParameter);
                    requestByCity(mZonaMunicipio);
                }
            }

            @Override
            public void onFailure(Call<ZonaWifiItem[]> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void requestByCity(Call<ZonaWifiItem[]> mZonItem) {
        mZonItem.enqueue(new Callback<ZonaWifiItem[]>() {
            @Override
            public void onResponse(Call<ZonaWifiItem[]> call, Response<ZonaWifiItem[]> response) {
                if (response.isSuccessful()) {
                    mainView.displayData(Arrays.asList(response.body()));
                    mainView.hideLoader();
                }
            }

            @Override
            public void onFailure(Call<ZonaWifiItem[]> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
