package com.example.health;

public class SignUpHelperClass {

    private String name;
    private String username;
    private String pass;

    public SignUpHelperClass() {

    }

    public SignUpHelperClass(String name, String username, String pass) {
        this.name = name;
        this.username = username;
        this.pass = pass;
    }

    public String getActualName() {
        return name;
    }

    public void setActualName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
