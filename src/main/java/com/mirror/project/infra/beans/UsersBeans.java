package com.mirror.project.infra.beans;

import com.mirror.project.core.gateway.AuthorizationGateway;
import com.mirror.project.core.gateway.UsersGateway;
import com.mirror.project.core.usecases.*;
import com.mirror.project.core.usecases.authenticate.AuthenticateUserUsecase;
import com.mirror.project.core.usecases.authenticate.AuthenticateUserUsecaseImpl;
import com.mirror.project.core.usecases.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersBeans {

    @Bean
    public CreateUserUsecase createUserUsecase(UsersGateway usersGateway, PasswordEncoder passwordEncoder) {
        return new CreateUserUsecaseImpl(usersGateway, passwordEncoder);
    }

    @Bean
    public AuthenticateUserUsecase authenticateUserUsecase(UsersGateway usersGateway, PasswordEncoder passwordEncoder) {
        return new AuthenticateUserUsecaseImpl(usersGateway, passwordEncoder);
    }

    @Bean
    public GetAllUsersUsecase getAllUsersUsecase(UsersGateway usersGateway, AuthorizationGateway authorizationGateway) {
        return new GetAllUsersUsecaseImpl(usersGateway, authorizationGateway);
    }
}
