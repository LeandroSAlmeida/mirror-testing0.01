package com.mirror.project.infra.mappers;

import com.mirror.project.core.domain.Page;
import com.mirror.project.core.domain.Users;
import com.mirror.project.infra.dtos.CreateUserRequest;
import com.mirror.project.infra.dtos.PageResponse;
import com.mirror.project.infra.dtos.UserResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UsersDTOMapper {

    public Users toUser(CreateUserRequest request) {
        return new Users(
                UUID.randomUUID().toString(),
                request.name(),
                request.email(),
                request.password(),
                "USER"
        );
    }

    public UserResponse toUserDTO(Users users) {
        return new UserResponse(
                users.id(),
                users.name(),
                users.email(),
                users.role()

        );
    }

    public PageResponse<UserResponse> toPageResponse(Page<Users> domainPage) {
        return new PageResponse<>(
                domainPage.content().stream()
                        .map(this::toUserDTO)
                        .collect(Collectors.toList()),
                domainPage.pageNumber(),
                domainPage.pageSize(),
                domainPage.totalPages(),
                domainPage.pageNumber(),
                domainPage.hasNext(),
                domainPage.hasPrevious(),
                domainPage.isEmpty()
        );
    }
}
