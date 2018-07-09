package com.coreman.zonaswifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coreman.zonaswifi.adapters.ZonaWifiAdapter;
import com.coreman.zonaswifi.models.ZonaWifiItem;
import com.coreman.zonaswifi.services.NetworkingService;
import com.coreman.zonaswifi.utils.NetworkHelper;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    AutoCompleteTextView mSearchText;
    List<ZonaWifiItem> mDataSet;
    ZonaWifiAdapter mAdapter;

    RecyclerView mRecyclerView;
    ProgressBar mProgressBar;

    private boolean networkOk;

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final ZonaWifiItem[] zonaWifiItems = (ZonaWifiItem[])
                    intent.getParcelableArrayExtra(NetworkingService.SERVICE_PAYLOAD);

            Log.i(TAG, "onHandleIntent:  Here we are "  + zonaWifiItems.length);

            mDataSet = Arrays.asList(zonaWifiItems);
            displayData();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_search).setOnClickListener(searchInfoByDepartment);
        mSearchText = findViewById(R.id.departament_search);

        networkOk = NetworkHelper.hasNetworkAcces(this);

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mBroadcastReceiver,
                new IntentFilter(NetworkingService.SERVICE_MESSAGE));

        mRecyclerView = findViewById(R.id.recycler_wifi_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mProgressBar = findViewById(R.id.progress_bar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mBroadcastReceiver);
    }

    private View.OnClickListener searchInfoByDepartment = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

            mProgressBar.setVisibility(View.VISIBLE);

            if (networkOk && !mSearchText.getText().toString().equals("")) {
                Intent callService = new Intent(MainActivity.this, NetworkingService.class);
                callService.putExtra(NetworkingService.SEARCH_QUERY, mSearchText.getText().toString());
                startService(callService);
            }
        }
    };

    private void displayData() {
        mProgressBar.setVisibility(View.INVISIBLE);
        if (mDataSet != null) {
            mAdapter = new ZonaWifiAdapter(this, mDataSet);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            Toast.makeText(this,
                    "No se encontro informacion para esta busqueda.",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // Menu Setup
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.items_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item_preferences) {
            Toast.makeText(this, "To be added", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
