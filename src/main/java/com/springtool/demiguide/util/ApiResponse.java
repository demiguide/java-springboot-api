package com.springtool.demiguide.util;

public class ApiResponse {
    private String status;
    private String code;
    private String detail;

    public ApiResponse(String status, String code, String detail) {
        this.status = status;
        this.code = code;
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
