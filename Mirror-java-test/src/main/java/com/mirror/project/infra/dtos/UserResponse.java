
package com.mirror.project.infra.dtos;

public record UserResponse(
        String id,
        String name,
        String email,
        String role
) {
}
