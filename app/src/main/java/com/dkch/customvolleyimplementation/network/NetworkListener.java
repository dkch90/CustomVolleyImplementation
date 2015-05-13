package com.dkch.customvolleyimplementation.network;

import android.content.Context;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.dkch.customvolleyimplementation.BuildConfig;
import com.dkch.customvolleyimplementation.R;


/**
 * Generic Volley Listener
 *
 * @author Dilip.Kumar.Chaudhary
 */
public class NetworkListener<T> implements Response.Listener<T>, Response.ErrorListener {
    private static final String TAG = "NetworkListener";

    private int mReqType;
    private Context mContext;
    private onUpdateListener mUpdateListener;

    public NetworkListener(Context context, onUpdateListener listener, int reqType) {
        mContext = context;
        mUpdateListener = listener;
        mReqType = reqType;
    }

    @Override
    public void onResponse(T response) {
        mUpdateListener.onUpdateView(response, true, mReqType);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMsg;
        if (error instanceof NoConnectionError) {
            errorMsg = mContext.getString(R.string.no_network_error);
        } else if (error instanceof ServerError) {
            errorMsg = mContext.getString(R.string.server_error);
        } else if (error instanceof TimeoutError) {
            errorMsg = mContext.getString(R.string.timeout_error);
        } else {
            errorMsg = mContext.getString(R.string.default_error);
        }
        if (BuildConfig.DEBUG) error.printStackTrace();
        mUpdateListener.onUpdateView(errorMsg, false, mReqType);
    }

    /**
     * Interface which is used to update UI after Network operation finish
     */
    public interface onUpdateListener {
        /**
         * Callback method called after Network Operation finish
         *
         * @param response
         * @param success
         * @param reqType
         */
        public void onUpdateView(Object response, boolean success, int reqType);
    }
}
