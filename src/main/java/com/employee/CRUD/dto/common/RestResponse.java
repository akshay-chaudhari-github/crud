package com.employee.CRUD.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse {
    private boolean success;
    private ApiError error;
    private String message;
    private Map<String, Object> data;

    @Data
    public class ApiError {
        private String message;
        private String code;

        public ApiError() {
            super();
        }

        public ApiError(String message, String code) {
            super();
            this.message = message;
            this.code = code;
        }
    }

    public RestResponse setError(String errorMessage, String errorCode) {
        setSuccess(false);
        error = new ApiError(errorMessage, errorCode);
        return this;
    }

    public RestResponse addData(String key, Object value) {
        if (null == data) {
            data = new HashMap<>();
        }
        data.put(key, value);
        return this;
    }

}
