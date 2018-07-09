package com.coreman.zonaswifi.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coreman.zonaswifi.R;
import com.coreman.zonaswifi.models.ZonaWifiItem;
import com.coreman.zonaswifi.ui.ZonasMapActivity;
import com.coreman.zonaswifi.utils.Constants;

import java.util.List;

public class ZonaWifiAdapter extends RecyclerView.Adapter<ZonaWifiAdapter.ViewHolder> {

    private Context mContext;
    private List<ZonaWifiItem> mWifiItems;

    public ZonaWifiAdapter(Context context, List<ZonaWifiItem> wifiItems) {
        this.mContext = context;
        this.mWifiItems = wifiItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(R.layout.list_item_wifi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ZonaWifiItem item = mWifiItems.get(position);

        holder.zoneName.setText(item.getNombreZonaWifi());
        holder.zoneLocation.setText(mContext.getString(R.string.zone_location, item.getDepartamento(), item.getMunicipio()));
        holder.zoneDirection.setText(item.getDireccion());
        holder.zoneStatus.setText(item.getEstadoDeLaZona());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMap = new Intent(mContext, ZonasMapActivity.class);
                openMap.putExtra(Constants.LOCATION_LATITUDE, item.getLatitud());
                openMap.putExtra(Constants.LOCATION_LONGITUDE, item.getLongitud());
                openMap.putExtra(Constants.LOCATION_TITLE, item.getNombreZonaWifi());
                mContext.startActivity(openMap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWifiItems.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView zoneName;
        public TextView zoneLocation;
        public TextView zoneDirection;
        public TextView zoneStatus;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);

            zoneName = itemView.findViewById(R.id.zone_name);
            zoneLocation = itemView.findViewById(R.id.zone_location);
            zoneDirection = itemView.findViewById(R.id.zone_direction);
            zoneStatus = itemView.findViewById(R.id.zone_status);
            mView = itemView;
        }
    }

}
