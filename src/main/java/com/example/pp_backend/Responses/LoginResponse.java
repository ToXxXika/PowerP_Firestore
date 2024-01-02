package com.example.pp_backend.Responses;
public class LoginResponse {
    private boolean success;
    private int status;

    public LoginResponse(boolean success, int status) {
        this.success = success;
        this.status = status;
    }

    // getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}