package com.robinbobin.testsdemo.model;

public enum QueueStatus {
    IN_PROGRESS("InProgress"),
    FAILED("Failed"),
    SUCCESS("Success");

    private String status;

    QueueStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return status;
    }
}
