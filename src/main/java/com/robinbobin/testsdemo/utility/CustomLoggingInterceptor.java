package com.robinbobin.testsdemo.utility;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomLoggingInterceptor implements Interceptor {
    private static Logger log = LoggerFactory.getLogger(CustomLoggingInterceptor.class);

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        log.info(getRequestLog(request));
        Response response = chain.proceed(request);
        log.info(getResponseLog(response));
        return response;
    }

    private String getRequestLog(Request request) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("<<<<<<<<<<<-REQUEST START->>>>>>>>>>>").append(System.lineSeparator())
                .append(request.method()).append(" ").append(request.url()).append(System.lineSeparator())
                .append(request.headers()).append(System.lineSeparator());

        if (request.body() != null) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            builder.append(buffer.readUtf8()).append(System.lineSeparator());
        }

        builder.append("<<<<<<<<<<<-REQUEST END->>>>>>>>>>>").append(System.lineSeparator());
        return builder.toString();
    }

    private String getResponseLog(Response response) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("<<<<<<<<<<<-RESPONSE START->>>>>>>>>>>").append(System.lineSeparator())
                .append(response.code()).append(" ").append(response.message()).append(System.lineSeparator())
                .append(response.headers()).append(System.lineSeparator());

        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.getBuffer();
        if (buffer.size() > 0) {
            builder.append(buffer.clone().readUtf8()).append(System.lineSeparator());
        }
        builder.append("<<<<<<<<<<<-RESPONSE END->>>>>>>>>>>>>").append(System.lineSeparator());

        return builder.toString();
    }

}
