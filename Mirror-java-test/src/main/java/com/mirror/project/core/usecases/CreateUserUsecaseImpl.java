package com.mirror.project.core.usecases;

import com.mirror.project.core.domain.Users;
import com.mirror.project.core.exceptions.BusinessException;
import com.mirror.project.core.gateway.UsersGateway;


public class CreateUserUsecaseImpl implements CreateUserUsecase {


    private final UsersGateway usersGateway;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUsecaseImpl(UsersGateway usersGateway, PasswordEncoder passwordEncoder) {
        this.usersGateway = usersGateway;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Users execute(Users user) {
        usersGateway.findByEmail(user.email()).ifPresent(existingUser -> {
            throw new BusinessException("Email already registered. Please use a different email address.");
        });

        String hashedPassword = passwordEncoder.encode(user.password());
        Users userWithHashedPassword = new Users(
                user.id(),
                user.name(),
                user.email(),
                hashedPassword,
                user.role()
        );
        return usersGateway.create(userWithHashedPassword);
    }
}
