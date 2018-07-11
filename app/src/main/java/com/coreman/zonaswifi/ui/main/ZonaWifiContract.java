package com.coreman.zonaswifi.ui.main;

import java.util.List;

public interface ZonaWifiContract {

    interface View {
        void showMessage(String message);
        void showError(String error);
        void showLoader();
        void hideLoader();
        void displayData(List<ZonaWifiItem> items);
    }

    interface Presenter {
        void doesItHaveInternet();
        void requestWifiPoints(String searchParameter);

    }
}
