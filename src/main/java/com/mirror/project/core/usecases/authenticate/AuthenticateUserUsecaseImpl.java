package com.mirror.project.core.usecases.authenticate;

import com.mirror.project.core.domain.Users;
import com.mirror.project.core.exceptions.InvalidCredentialsException;
import com.mirror.project.core.gateway.UsersGateway;
import com.mirror.project.core.usecases.PasswordEncoder;

public class AuthenticateUserUsecaseImpl implements AuthenticateUserUsecase {

    private final UsersGateway usersGateway;
    private final PasswordEncoder passwordEncoder;

    public AuthenticateUserUsecaseImpl(UsersGateway usersGateway, PasswordEncoder passwordEncoder) {
        this.usersGateway = usersGateway;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users execute(String email, String password) {
        Users user = usersGateway.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password. Please check your credentials."));
        if(!passwordEncoder.matches(password, user.password())) {
            throw new InvalidCredentialsException("Invalid email or password. Please check your credentials.");
        }
        return user;
    }
}
