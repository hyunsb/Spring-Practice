package com.example.developermaker.exception;

import com.example.developermaker.dto.DeveloperMakerErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.example.developermaker.exception.DeveloperMakerErrorCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class DeveloperMakerExceptionHandler {

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DeveloperMakerException.class)
    public DeveloperMakerErrorResponse handleDeveloperMakerException(DeveloperMakerException exception, HttpServletRequest request) {

        log.error("errorCode: {}, url: {}, message: {}"
                , exception.getErrorCode(), request.getRequestURI(), exception.getDetailMessage());
        return new DeveloperMakerErrorResponse(exception.getErrorCode(), exception.getDetailMessage());
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class, MethodArgumentNotValidException.class})
    public DeveloperMakerErrorResponse handleBadRequest(DeveloperMakerException exception, HttpServletRequest request) {

        log.error("url: {}, message: {}", request.getRequestURI(), exception.getDetailMessage());

        return new DeveloperMakerErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public DeveloperMakerErrorResponse handleException(DeveloperMakerException exception, HttpServletRequest request) {

        log.error("url: {}, message: {}", request.getRequestURI(), exception.getDetailMessage());

        return new DeveloperMakerErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getMessage());
    }
}
