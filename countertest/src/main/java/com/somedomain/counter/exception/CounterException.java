package com.somedomain.counter.exception;


public class CounterException extends Exception {

    private String message;

    public CounterException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
