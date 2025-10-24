package com.mirror.project.core.domain;

public record Users(
        String id,
        String name,
        String email,
        String password,
        String role
) {
    public Users {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (password == null || password.isBlank()){
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        if (password.length() < 8){
            throw new IllegalArgumentException("password must be at least 8 characters long");
        }
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
    }

    public Users(String id, String name, String email, String password) {
        this(id, name, email, password, "USER");
    }
}
