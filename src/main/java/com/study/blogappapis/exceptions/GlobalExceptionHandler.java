package com.study.blogappapis.exceptions;

import com.study.blogappapis.payloads.ApiResponse;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException) {
        String msg=resourceNotFoundException.getMessage();
        ApiResponse apiResponse=new ApiResponse(msg,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String,String> map=new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(objectError ->  {
            String fieldName=((FieldError)objectError).getField();
            String message=objectError.getDefaultMessage();
            map.put(fieldName,message);
        });
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<String> propertyReferenceExceptionHandler(PropertyReferenceException propertyReferenceException) {
        String res = propertyReferenceException.getMessage();
        return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
    }
}
