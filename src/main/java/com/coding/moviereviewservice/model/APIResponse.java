package com.coding.moviereviewservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {

    private HttpStatus status;

    private String message;

    private Object data;

    public static APIResponse success() {
        return new APIResponse(HttpStatus.OK, null, null);
    }

    public static APIResponse success(String message) {
        return new APIResponse(HttpStatus.OK, message, null);
    }

    public static APIResponse success(Object data) {
        return new APIResponse(HttpStatus.OK, null, data);
    }

    public static APIResponse success(String message, Object data) {
        return new APIResponse(HttpStatus.OK, message, data);
    }

    public static APIResponse error() {
        return new APIResponse(HttpStatus.BAD_REQUEST, null, null);
    }

    public static APIResponse error(String message) {
        return new APIResponse(HttpStatus.BAD_REQUEST, message, null);
    }

    public static APIResponse error(Object data) {
        return new APIResponse(HttpStatus.BAD_REQUEST, null, data);
    }

    public static APIResponse error(String message, Object data) {
        return new APIResponse(HttpStatus.BAD_REQUEST, message, data);
    }
}
