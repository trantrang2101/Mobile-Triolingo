package com.example.triolingo_mobile.Model;

public class UserModel {
    private String Username;
    private String password;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel() {
    }

    public UserModel(String username, String password) {
        Username = username;
        this.password = password;
    }
}
