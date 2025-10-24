package com.mirror.project.infra.security;

import com.mirror.project.core.usecases.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordEncoderAdapter() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public String encode(String plainText) {
        return bCryptPasswordEncoder.encode(plainText);
    }

    @Override
    public boolean matches(String plainText, String hashedPassword) {
        return bCryptPasswordEncoder.matches(plainText, hashedPassword);
    }
}
