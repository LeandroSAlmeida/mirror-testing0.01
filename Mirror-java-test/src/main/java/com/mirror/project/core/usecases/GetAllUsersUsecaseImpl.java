package com.mirror.project.core.usecases;

import com.mirror.project.core.domain.Page;
import com.mirror.project.core.domain.Users;
import com.mirror.project.core.exceptions.ForbiddenException;
import com.mirror.project.core.exceptions.UnauthorizedException;
import com.mirror.project.core.gateway.AuthorizationGateway;
import com.mirror.project.core.gateway.UsersGateway;

public class GetAllUsersUsecaseImpl implements GetAllUsersUsecase  {

    private final UsersGateway usersGateway;
    private final AuthorizationGateway authorizationGateway;

    public GetAllUsersUsecaseImpl(UsersGateway usersGateway, AuthorizationGateway authorizationGateway) {
        this.usersGateway = usersGateway;
        this.authorizationGateway = authorizationGateway;
    }


    @Override
    public Page<Users> execute(int page, int size) {

        if(!authorizationGateway.isAuthenticated()) {
            throw new UnauthorizedException("Authentication required. Please login to access this resource.");
        }
        if (!authorizationGateway.hasRole("ADMIN")) {
            throw new ForbiddenException("Access denied. You do not have authorization to access this resource.");
        }
        if (page < 0) {
            throw new IllegalArgumentException("Page number must be non-negative.");
        };
        if (size < 1) {
            throw new IllegalArgumentException("Page size must be positive.");
        }
        return usersGateway.findAll(page, size);
    }
}
