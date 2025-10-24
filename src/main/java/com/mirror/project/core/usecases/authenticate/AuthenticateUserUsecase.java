package com.mirror.project.core.usecases.authenticate;

import com.mirror.project.core.domain.Users;

public interface AuthenticateUserUsecase {
    Users execute(String email, String password);
}
