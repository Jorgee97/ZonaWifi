package com.coreman.zonaswifi.utils;

import android.net.Uri;

import com.coreman.zonaswifi.services.NetworkingService;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public final static String BASE_API_URL = "https://www.datos.gov.co/resource/hbap-jitp.json";

    public static URL buildURL(String department) {
        Uri buildUri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(NetworkingService.DEPARTMENT_QUERY, department)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
