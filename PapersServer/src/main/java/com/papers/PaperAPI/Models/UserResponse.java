package com.papers.PaperAPI.Models;

public class UserResponse {
    private String username;

    public UserResponse(String username) {
        this.username = username;
    }

    public UserResponse() {}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
