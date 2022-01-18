package com.robinbobin.testsdemo.http.request;


import com.robinbobin.testsdemo.http.HttpRequest;
import com.robinbobin.testsdemo.http.HttpResponse;
import com.robinbobin.testsdemo.model.Person;
import com.robinbobin.testsdemo.TestConfig;
import com.robinbobin.testsdemo.utility.JsonUtility;
import okhttp3.Headers;

public class AddPerson {

    public static HttpResponse send(Person person) {
        Headers headers = Headers.of(
                "Authorization", TestConfig.getToken(),
                "content-type", "application/json");
        String body = JsonUtility.toJson(person);
        return HttpRequest.post(TestConfig.getServiceUrl() + "/v1/person/", headers, body);
    }
}
