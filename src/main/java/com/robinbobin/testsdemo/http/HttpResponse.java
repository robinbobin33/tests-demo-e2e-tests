package com.robinbobin.testsdemo.http;

import com.robinbobin.testsdemo.utility.JsonUtility;
import okhttp3.Headers;

public class HttpResponse {

    private int statusCode;
    private String body;
    private Headers headers;

    public HttpResponse(int statusCode, Headers headers, String body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public <T> T deserializeBody(Class<T> clazz) {
        return JsonUtility.toObject(body, clazz);
    }

    public int getStatus() {
        return statusCode;
    }

    public boolean isStatusOk() {
        return statusCode == 200;
    }

    public boolean isStatusCreated() {
        return statusCode == 201;
    }
}
