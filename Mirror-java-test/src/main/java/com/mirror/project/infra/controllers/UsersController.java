package com.mirror.project.infra.controllers;

import com.mirror.project.core.domain.Page;
import com.mirror.project.core.domain.Users;
import com.mirror.project.core.usecases.CreateUserUsecase;
import com.mirror.project.core.usecases.GetAllUsersUsecase;
import com.mirror.project.infra.dtos.CreateUserRequest;
import com.mirror.project.infra.dtos.PageResponse;
import com.mirror.project.infra.dtos.UserResponse;
import com.mirror.project.infra.mappers.UsersDTOMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final CreateUserUsecase createUserUsecase;
    private final GetAllUsersUsecase getAllUsersUsecase;
    private final UsersDTOMapper usersDTOMapper;

    public UsersController(CreateUserUsecase createUserUsecase, GetAllUsersUsecase getAllUsersUsecase, UsersDTOMapper usersDTOMapper) {
        this.createUserUsecase = createUserUsecase;
        this.getAllUsersUsecase = getAllUsersUsecase;
        this.usersDTOMapper = usersDTOMapper;
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        Users usersDomain = usersDTOMapper.toUser(request);
        Users createdUser = createUserUsecase.execute(usersDomain);
        UserResponse usersDTOResponse = usersDTOMapper.toUserDTO(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(usersDTOResponse);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageResponse<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Users> usersPage = getAllUsersUsecase.execute(page, size);
        PageResponse<UserResponse> response = usersDTOMapper.toPageResponse(usersPage);
        return ResponseEntity.ok(response);
    }
}
