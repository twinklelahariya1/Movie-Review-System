package com.coding.moviereviewservice.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {

    public enum Code {
        SERVICE_EXCEPTION, DATA_NOT_FOUND_EXCEPTION
    }

    private Code code;
    private String message;

    public CustomException() {
    }

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(Code code) {
        this.code = code;
    }

    public CustomException(String message, Code exception) {
        this.code = exception;
        this.message = message;
    }

    //TODO:factory pattern can be used

    public CustomException dataNotFound() {
        return new CustomException(Code.DATA_NOT_FOUND_EXCEPTION);
    }

    public CustomException dataNotFound(String message) {
        return new CustomException(message, Code.DATA_NOT_FOUND_EXCEPTION);
    }

    public CustomException serviceException() {
        return new CustomException(Code.SERVICE_EXCEPTION);
    }

    public CustomException serviceException(String message) {
        return new CustomException(message, Code.SERVICE_EXCEPTION);
    }
}
