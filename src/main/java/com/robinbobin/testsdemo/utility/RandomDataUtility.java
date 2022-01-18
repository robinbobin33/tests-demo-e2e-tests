package com.robinbobin.testsdemo.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.robinbobin.testsdemo.model.Person;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RandomDataUtility {

    public static String getRandomBirthDate() {
        return LocalDate.now()
                .minusDays(RandomUtils.nextLong(100L, 1000L))
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getRandomPhone() {
        return "7800" + RandomUtils.nextLong(1000000L, 9999999L);
    }

    public static String getRandomFio() {
        return RandomStringUtils.randomAlphabetic(3, 5) + " " +
                RandomStringUtils.randomAlphabetic(3, 5) + " " +
                RandomStringUtils.randomAlphabetic(3, 5);
    }

    public static Person getRandomPerson() {
        return new Person().setBirthDate(getRandomBirthDate())
                .setPhone(getRandomPhone())
                .setFio(getRandomFio());
    }

}