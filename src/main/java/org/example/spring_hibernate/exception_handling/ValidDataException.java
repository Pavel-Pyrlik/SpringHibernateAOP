package org.example.spring_hibernate.exception_handling;

public class ValidDataException extends RuntimeException{
    public ValidDataException(String message) {
        super(message);
    }
}
