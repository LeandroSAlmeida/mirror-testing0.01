package com.mirror.project.infra.controllers;

import com.mirror.project.core.domain.Users;
import com.mirror.project.core.usecases.authenticate.AuthenticateUserUsecase;
import com.mirror.project.infra.dtos.LoginRequest;
import com.mirror.project.infra.dtos.LoginResponse;
import com.mirror.project.infra.dtos.UserResponse;
import com.mirror.project.infra.mappers.UsersDTOMapper;
import com.mirror.project.infra.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticateUserUsecase authenticateUserUseCase;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsersDTOMapper usersDTOMapper;

    public AuthController(AuthenticateUserUsecase authenticateUserUseCase, JwtTokenProvider jwtTokenProvider, UsersDTOMapper usersDTOMapper) {
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.jwtTokenProvider = jwtTokenProvider;
        this.usersDTOMapper = usersDTOMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        Users authenticateUser = authenticateUserUseCase.execute(request.email(), request.password());
        String token = jwtTokenProvider.createToken(authenticateUser.email(), authenticateUser.role());
        UserResponse userResponse = usersDTOMapper.toUserDTO(authenticateUser);
        LoginResponse response = new LoginResponse(token, userResponse);
        return ResponseEntity.ok(response);
    }

}
