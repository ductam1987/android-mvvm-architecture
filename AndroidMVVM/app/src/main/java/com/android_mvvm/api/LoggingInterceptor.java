package com.android_mvvm.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tam Nguyen on 1/8/17.
 */

public class LoggingInterceptor implements Interceptor {

    private static String TAG = LoggingInterceptor.class.getName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.d(TAG,(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers())));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.d(TAG,(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers())));

        return response;
    }
}
