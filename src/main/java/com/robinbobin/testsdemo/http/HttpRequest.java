package com.robinbobin.testsdemo.http;

import com.robinbobin.testsdemo.utility.CustomLoggingInterceptor;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import java.io.IOException;

public class HttpRequest {

    private static OkHttpClient client;

    static {
        CustomLoggingInterceptor interceptor = new CustomLoggingInterceptor();
        client = new OkHttpClient.Builder()
                .followRedirects(false)
                .addInterceptor(interceptor)
                .build();
    }

    public static HttpResponse get(String url, Headers headers) {
        Request request = new Request.Builder()
                .method("GET", null)
                .url(url)
                .headers(headers)
                .build();
        return sendRequest(request);
    }

    public static HttpResponse post(String url, Headers headers, String body) {
        Request request = new Request.Builder()
                .method("POST", RequestBody.create(MediaType.parse("application/json"), body))
                .url(url)
                .headers(headers)
                .build();
        return sendRequest(request);
    }

    public static HttpResponse delete(String url, Headers headers) {
        Request request = new Request.Builder()
                .method("DELETE", null)
                .url(url)
                .headers(headers)
                .build();

        return sendRequest(request);
    }

    private static HttpResponse sendRequest(Request request) {
        try (Response response = client.newCall(request).execute()) {
            return new HttpResponse(response.code(), response.headers(), response.body().string());
        } catch (IOException ex) {
            throw new RuntimeException("Failed to send request", ex);
        }
    }

}
