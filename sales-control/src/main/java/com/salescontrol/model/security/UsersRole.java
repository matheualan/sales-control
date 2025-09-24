package com.salescontrol.model.security;

public enum UsersRole {

    ADMIN("admin"), MANAGER("manager"), USER("user");

    private final String role;

    UsersRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}