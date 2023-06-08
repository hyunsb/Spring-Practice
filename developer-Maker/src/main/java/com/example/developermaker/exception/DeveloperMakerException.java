package com.example.developermaker.exception;

public class DeveloperMakerException extends RuntimeException {

    private final DeveloperMakerErrorCode errorCode;
    private final String detailMessage;

    public DeveloperMakerException(DeveloperMakerErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public DeveloperMakerException(DeveloperMakerErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }
}
