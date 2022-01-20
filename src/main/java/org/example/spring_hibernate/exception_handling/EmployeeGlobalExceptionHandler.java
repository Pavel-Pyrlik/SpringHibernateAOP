package org.example.spring_hibernate.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectDate> handleException(NoSuchEmployeeException exception) {

        EmployeeIncorrectDate date = new EmployeeIncorrectDate();
        date.setInfo(exception.getMessage());
        return new ResponseEntity<>(date, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectDate> handleException(Exception exception) {

        EmployeeIncorrectDate date = new EmployeeIncorrectDate();
        date.setInfo(exception.getMessage());
        return new ResponseEntity<>(date, HttpStatus.NOT_FOUND);
    }
}
