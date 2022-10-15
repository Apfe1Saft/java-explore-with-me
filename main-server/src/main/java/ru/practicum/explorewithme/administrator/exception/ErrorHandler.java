package ru.practicum.explorewithme.administrator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.explorewithme.model.error.ApiError;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleInternalServerException(final InternalServerException e) {
        ApiError error = new ApiError();
        error.setStatus("INTERNAL_SERVER_ERROR");
        error.setReason("\"could not execute statement; SQL [n/a]; constraint [uq_category_name]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement\"");
        error.setMessage(e.getMessage());
        error.setTimestamp(LocalDateTime.now());
        return error;

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(final BadRequestException e) {
        return new ApiError(new ArrayList<>(), e.getMessage(), "For the requested operation the conditions are not met.",
                "FORBIDDEN", LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictException(final ConflictException e) {
        ApiError error = new ApiError();
        error.setStatus("CONFLICT");
        error.setReason("Integrity constraint has been violated");
        error.setMessage(e.getMessage());
        error.setTimestamp(LocalDateTime.now());
        return error;

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final NotFoundException e) {
        ApiError error = new ApiError();
        error.setStatus("NOT_FOUND");
        error.setReason("The required object not found");
        error.setMessage(e.getMessage());
        error.setTimestamp(LocalDateTime.now());
        return error;

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleForbiddenException(final ForbiddenException e) {
        return new ApiError(new ArrayList<>(), e.getMessage(), "For the requested operation the conditions are not met.",
                "FORBIDDEN", LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        return new ApiError(new ArrayList<>(), e.getMessage(), "For the requested operation the conditions are not met.",
                "FORBIDDEN", LocalDateTime.now());
    }
}
