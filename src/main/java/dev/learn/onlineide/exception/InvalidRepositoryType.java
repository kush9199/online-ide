package dev.learn.onlineide.exception;

public class InvalidRepositoryType extends RuntimeException {
    public InvalidRepositoryType(String message) {
        super(message);
    }
}
