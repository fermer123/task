package ru.demo.task.domain.exeption;

public class ResourceNotFoundException extends Exception {
    ResourceNotFoundException(String message) {
        super(message);
    }
}
