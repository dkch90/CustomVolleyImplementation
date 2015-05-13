package com.dkch.customvolleyimplementation.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dkch.customvolleyimplementation.R;
import com.dkch.customvolleyimplementation.network.NetworkListener;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements NetworkListener.onUpdateListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Network Request
        Map<String, String> map = new HashMap<>();

       /* NetworkRequest networkRequest = new NetworkRequest(getBaseContext(), NetworkAPI.URL, Place.class, new NetworkUpdateListener(getBaseContext(), this, NetworkAPI.REQ_URL), map);
        NearbyAppController.getInstance().addToRequestQueue(networkRequest, TAG);*/
    }

    @Override
    public void onUpdateView(Object response, boolean success, int reqType) {

    }
}
