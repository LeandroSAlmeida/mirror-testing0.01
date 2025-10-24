package com.mirror.project.core.gateway;

import com.mirror.project.core.domain.Page;
import com.mirror.project.core.domain.Users;

import java.util.Optional;

public interface UsersGateway {
    Users create(Users user);
    Optional<Users> findByEmail(String email);
    Page<Users> findAll(int page, int size);
}
