package com.mirror.project.core.usecases;

public interface PasswordEncoder {
    String encode(String plainText);
    boolean matches(String plainText, String hashedPassword);
}
