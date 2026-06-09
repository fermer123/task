package ru.demo.task.domain.exeption;

import java.util.Map;

public class ExeptionBody extends Exception {
    private String message;
    private Map<String, String> errors;


    public ExeptionBody(String message) {
        super(message);
    }
}
