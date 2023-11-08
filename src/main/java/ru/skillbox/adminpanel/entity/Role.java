package ru.skillbox.adminpanel.entity;

public enum Role {
    ADMIN, MODER;

    public boolean equalsIgnoreCase(String role) {
        return this.name().equalsIgnoreCase(role);
    }
}
