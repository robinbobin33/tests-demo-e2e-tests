package com.robinbobin.testsdemo;

import com.robinbobin.testsdemo.model.Person;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;

public class TestConfig {
    private static Config config = ConfigFactory.load();

    public static Person getDefaultPerson() {
        return ConfigBeanFactory.create(config.getConfig("person.default"), Person.class);
    }

    public static String getServiceUrl() {
        return config.getString("workshop-service.url");
    }

    public static String getToken() {
        return config.getString("workshop-service.token");
    }

}
