
package com.mirror.project.infra.mappers;

import com.mirror.project.core.domain.Page;
import com.mirror.project.core.domain.Users;
import com.mirror.project.infra.persistence.UsersEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersEntityMapper {

    public UsersEntity toEntity(Users domain) {
        return new UsersEntity(
                domain.id(),
                domain.name(),
                domain.email(),
                domain.password(),
                domain.role()
        );
    }

    public Users toDomain(UsersEntity entity) {
        return new Users(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole()
        );
    }

    public Page<Users> toDomainPage(List<UsersEntity> entities, int page, int size, long total) {
        List<Users> users = entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());

        int totalPages = (int) Math.ceil((double) total / size);

        return new Page<>(users, page, size, total, totalPages);
    }
}
