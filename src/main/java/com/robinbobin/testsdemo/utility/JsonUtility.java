package com.robinbobin.testsdemo.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtility {
    private static ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Conversion to Object has failed. Json: " + json, ex);
        }
    }

    public static String toJson(Object raw) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(raw);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Conversion to Json has failed. Object: " + raw.toString(), ex);
        }
    }


}
