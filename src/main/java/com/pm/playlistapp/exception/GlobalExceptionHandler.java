package com.pm.playlistapp.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Handles:
     * - ResourceNotFoundException
     * - ForbiddenOperationException
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(
            ApiException exception
    ) {
        Map<String, Object> body = createBody(
                exception.getStatus(),
                exception.getMessage()
        );

        return ResponseEntity
                .status(exception.getStatus())
                .body(body);
    }

    /*
     * Handles validation inside @Valid @RequestBody.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleBodyValidation(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(
                        error.getField(),
                        error.getDefaultMessage()
                ));

        Map<String, Object> body = createBody(
                HttpStatus.BAD_REQUEST,
                "Validation failed"
        );

        body.put("errors", errors);

        return ResponseEntity.badRequest().body(body);
    }

    /*
     * Handles @Min, @Max, on controller parameters
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, Object>> handleParameterValidation(
            HandlerMethodValidationException exception
    ) {
        List<String> errors = exception.getParameterValidationResults()
                .stream()
                .flatMap(result -> result.getResolvableErrors().stream())
                .map(error -> error.getDefaultMessage() == null
                        ? "Invalid value"
                        : error.getDefaultMessage())
                .toList();

        Map<String, Object> body = createBody(
                HttpStatus.BAD_REQUEST,
                "Validation failed"
        );

        body.put("errors", errors);

        return ResponseEntity.badRequest().body(body);
    }

    /*
     * Handles database unique-constraint violations.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDatabaseConflict(
            DataIntegrityViolationException exception
    ) {
        Map<String, Object> body = createBody(
                HttpStatus.CONFLICT,
                "This record already exists"
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    private Map<String, Object> createBody(
            HttpStatus status,
            String message
    ) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("status", status.value());
        body.put("message", message);

        return body;
    }
}