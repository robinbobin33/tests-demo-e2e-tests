package com.robinbobin.testsdemo.http.request;

import com.robinbobin.testsdemo.TestConfig;
import com.robinbobin.testsdemo.http.HttpRequest;
import com.robinbobin.testsdemo.http.HttpResponse;
import okhttp3.Headers;

public class GetPerson {

    public static HttpResponse send(String personId) {
        Headers headers = Headers.of("Authorization", TestConfig.getToken());
        return HttpRequest.get(TestConfig.getServiceUrl() + "/v1/person/" + personId, headers);
    }
}
