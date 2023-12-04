package com.example.demo.exceptions;

import com.example.demo.payloads.FieldErrors;
import com.example.demo.payloads.ResponseBodyApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseBodyApi> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message=ex.getMessage();
        ResponseBodyApi resBody=new ResponseBodyApi();
        resBody.setResponseCode("11");
        resBody.setResponseDescription(message);
        return ResponseEntity.ok(resBody);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBodyApi<List<FieldErrors>>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message="Format Error";
        ResponseBodyApi<List<FieldErrors>> resBody=new ResponseBodyApi<>();
        resBody.setResponseCode("30");
        List<ObjectError> temp=ex.getBindingResult().getAllErrors();
        List<FieldErrors> fieldErrorsList =new ArrayList<>();
        for (ObjectError objectError : temp) {
            if (objectError instanceof FieldError fieldError) {
                FieldErrors fieldErrorsResponse=new FieldErrors();
                fieldErrorsResponse.setFieldError(fieldError.getDefaultMessage());
                fieldErrorsResponse.setFieldName(fieldError.getField());
                fieldErrorsList.add(fieldErrorsResponse);
            }
        }
        resBody.setResponseDescription(message);
        resBody.setDetails(fieldErrorsList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resBody);

    }

}
