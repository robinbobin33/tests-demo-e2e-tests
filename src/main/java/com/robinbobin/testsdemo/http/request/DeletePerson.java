package com.robinbobin.testsdemo.http.request;


import com.robinbobin.testsdemo.TestConfig;
import com.robinbobin.testsdemo.http.HttpRequest;
import com.robinbobin.testsdemo.http.HttpResponse;
import okhttp3.Headers;

public class DeletePerson {

    public static HttpResponse send(String phone) {
        Headers headers = Headers.of("Authorization", TestConfig.getToken());
        return HttpRequest.delete(TestConfig.getServiceUrl() + "/v1/person/" + phone, headers);
    }
}
