package com.bookapp.exception;

import com.bookapp.models.ApiErrors;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
/*with this annotation,
spring framework will get to know that all the exceptions are handled in this class */

/*
In order to handle all the generic exceptions,
class must extend ResponseEntityExceptionHandler
 */
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //overriding the required methods

    //in this method, we are returning the status and body which has error object when exception occurs
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Request method not supported");
        ApiErrors errors = new ApiErrors(message, details, status, LocalDateTime.now());
        //return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
        return ResponseEntity.status(status).body(errors);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Media type not supported");
        details.add(ex.getMessage());
        ApiErrors errors = new ApiErrors(message, details, status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Path variable is missing");
        ApiErrors errors = new ApiErrors(message, details,status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Request param is missing");
        ApiErrors errors = new ApiErrors(message, details,status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
         }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Mismatch of Type");
        ApiErrors errors = new ApiErrors(message, details,status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Request body is not readable");
        ApiErrors errors = new ApiErrors(message, details,status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex){
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Book not found");
        ApiErrors errors = new ApiErrors(message, details,HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Object> idNotFoundException(IdNotFoundException ex){
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Id not available");
        ApiErrors errors = new ApiErrors(message, details,HttpStatus.NOT_FOUND, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    /*
    Since we cant handle each and every exception, we need to create a fallback handler which handles all the other exceptions apart from above,
    like IllegalArgument, SQL exception, DataAccessException etc.,
    * */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception ex){
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("other exceptions");
        details.add(ex.getMessage());
        ApiErrors errors = new ApiErrors(message, details,HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
