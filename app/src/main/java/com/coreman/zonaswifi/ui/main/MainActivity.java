package com.coreman.zonaswifi.ui.main;

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

import com.coreman.zonaswifi.R;
import com.coreman.zonaswifi.services.NetworkingService;
import com.coreman.zonaswifi.utils.NetworkHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ZonaWifiContract.View {
    private static final String TAG = "MainActivity";

    AutoCompleteTextView mSearchText;
    ZonaWifiAdapter mAdapter;

    RecyclerView mRecyclerView;
    ProgressBar mProgressBar;

    private boolean networkOk;

    ZonaWifiPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mPresenter = new ZonaWifiPresenter(this, networkOk);
    }

    private void initViews() {
        mSearchText = findViewById(R.id.departament_search);
        mProgressBar = findViewById(R.id.progress_bar);

        mRecyclerView = findViewById(R.id.recycler_wifi_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        networkOk = NetworkHelper.hasNetworkAcces(this);

        findViewById(R.id.btn_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                mPresenter.doesItHaveInternet();
                mPresenter.requestWifiPoints(mSearchText.getText().toString());
                break;
        }
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this,
                error,
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showLoader() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayData(List<ZonaWifiItem> items) {
        if (items != null) {
            mAdapter = new ZonaWifiAdapter(this, items);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    //region Menu Setup
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
    //endregion
}
