package com.example.metlife.nisha;

import java.io.Serializable;

public class User implements Serializable {
    private String role;
    private String userId;
    private String password;

    public User(String role, String userId, String password) {
        this.role = role;
        this.userId = userId;
        this.password = password;
    }

    public String getRole() { return role; }
    public String getUserId() { return userId; }
    public String getPassword() { return password; }
}