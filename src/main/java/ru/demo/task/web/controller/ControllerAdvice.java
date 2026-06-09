package ru.demo.task.web.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.demo.task.domain.exeption.ExeptionBody;
import ru.demo.task.domain.exeption.ResourceMappingException;
import ru.demo.task.domain.exeption.ResourceNotFoundException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExeptionBody handleResourceNotFound(ResourceNotFoundException e) {
        return new ExeptionBody(e.getMessage());
    }

    @ExceptionHandler(ResourceMappingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExeptionBody handleResourceMapping(ResourceMappingException e) {
        return new ExeptionBody(e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExeptionBody handleIllegalState(IllegalStateException e) {
        return new ExeptionBody(e.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class, org.springframework.security.access.AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExeptionBody handleAccessDenied(AccessDeniedException e) {
        return new ExeptionBody("Access denied");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExeptionBody handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return new ExeptionBody("Validation failed");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExeptionBody handleConstraintViolation(ConstraintViolationException e) {
        return new ExeptionBody("Validation failed");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExeptionBody handleException(Exception e) {
        return new ExeptionBody("INTERNAL_SERVER_ERROR");
    }
}
