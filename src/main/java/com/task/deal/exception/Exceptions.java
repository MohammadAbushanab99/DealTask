package com.task.deal.exception;

public class Exceptions {

    public class DealValidationException extends RuntimeException {
        public DealValidationException(String message) {
            super(message);
        }
    }
}
