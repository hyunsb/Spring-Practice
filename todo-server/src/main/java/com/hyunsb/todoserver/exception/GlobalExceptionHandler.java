package com.hyunsb.todoserver.exception;

import com.hyunsb.todoserver.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponseDto<String> handleException(Exception exception) {
        return new ErrorResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
}
