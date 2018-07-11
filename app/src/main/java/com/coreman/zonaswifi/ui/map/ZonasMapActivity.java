package com.coreman.zonaswifi.ui.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coreman.zonaswifi.R;
import com.coreman.zonaswifi.utils.Constants;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Map;

public class ZonasMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            double lat = Double.parseDouble(extra.getString(Constants.LOCATION_LATITUDE));
            double lon = Double.parseDouble(extra.getString(Constants.LOCATION_LONGITUDE));
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lon))
                    .title(extra.getString(Constants.LOCATION_TITLE))
            );
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 12));
        }
    }
}
