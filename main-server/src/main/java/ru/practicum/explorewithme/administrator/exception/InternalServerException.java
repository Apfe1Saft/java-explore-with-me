package ru.practicum.explorewithme.administrator.exception;

public class InternalServerException extends RuntimeException {
    public InternalServerException(final String message) {
        super(message);
    }
}
