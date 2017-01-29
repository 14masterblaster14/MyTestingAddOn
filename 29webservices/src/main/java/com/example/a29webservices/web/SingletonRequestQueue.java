package com.example.a29webservices.web;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by DELL on 28-01-2017.
 */

public class SingletonRequestQueue {
    private static RequestQueue requestQueue;

    public static RequestQueue myQueue(Context context) {
        if (requestQueue == null) requestQueue = Volley.newRequestQueue(context);
        return requestQueue;
    }
}
