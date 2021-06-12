package com.coding.moviereviewservice.model;


import com.coding.moviereviewservice.enums.APIResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {

    private APIResponseStatus status;

    private String message;

    private Object data;


    public static APIResponse success() {
        return new APIResponse(APIResponseStatus.SUCCESS, null, null);
    }

    public static APIResponse success(String message) {
        return new APIResponse(APIResponseStatus.SUCCESS, message, null);
    }

    public static APIResponse success(Object data) {
        return new APIResponse(APIResponseStatus.SUCCESS, null, data);
    }

    public static APIResponse success(String message, Object data) {
        return new APIResponse(APIResponseStatus.SUCCESS, message, data);
    }

    public static APIResponse error() {
        return new APIResponse(APIResponseStatus.ERROR, null, null);
    }

    public static APIResponse error(String message) {
        return new APIResponse(APIResponseStatus.ERROR, message, null);
    }

    public static APIResponse error(Object data) {
        return new APIResponse(APIResponseStatus.ERROR, null, data);
    }

    public static APIResponse error(String message, Object data) {
        return new APIResponse(APIResponseStatus.ERROR, message, data);
    }
}
