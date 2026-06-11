package ru.demo.task.domain.exeption;

import lombok.Data;

import java.util.Map;

@Data
public class ExeptionBody {
    private String message;
    private Map<String, String> errors;


    public ExeptionBody(String message) {
        this.message = message;
    }
}
