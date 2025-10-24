package com.mirror.project.infra.repositories;

import com.mirror.project.infra.persistence.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {

    Optional<UsersEntity> findByEmail(String email);
}
