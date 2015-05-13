package com.dkch.customvolleyimplementation.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.dkch.customvolleyimplementation.BuildConfig;

/**
 * Bitmap cache class
 *
 * @author Dilip.Kumar.Chaudhary
 */
public class VolleyBitmapCache extends LruCache<String, Bitmap> implements
        ImageCache {
    private static final String TAG = "VolleyBitmapCache";
    private static final int MAX_SIZE = 1024;
    private static final int SAMPLE_SIZE = 8;

    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / MAX_SIZE);
        final int cacheSize = maxMemory / SAMPLE_SIZE;
        if (BuildConfig.DEBUG) Log.d(TAG, "Memory will use " + cacheSize);
        return cacheSize;
    }

    public VolleyBitmapCache() {
        this(getDefaultLruCacheSize());
    }

    public VolleyBitmapCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
}