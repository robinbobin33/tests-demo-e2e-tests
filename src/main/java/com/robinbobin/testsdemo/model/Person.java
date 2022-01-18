package com.robinbobin.testsdemo.model;

import java.util.Objects;

public class Person {
    private String fio;
    private String phone;
    private String birthDate;

    public String getFio() {
        return fio;
    }

    public Person setFio(String fio) {
        this.fio = fio;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Person setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Person setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Override
    public String toString() {
        return "Person(fio: " + fio + ", phone: " + phone + ", birthDate: " + birthDate + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Person)) {
            return false;
        } else {
            Person other = (Person) obj;
            return Objects.equals(this.fio, other.fio) &&
                    Objects.equals(this.birthDate, other.birthDate) &&
                    Objects.equals(this.phone, other.phone);
        }
    }

}
