package com.mirror.project.infra.gateway;

import com.mirror.project.core.domain.Page;
import com.mirror.project.core.domain.Users;
import com.mirror.project.core.gateway.UsersGateway;
import com.mirror.project.infra.mappers.UsersEntityMapper;
import com.mirror.project.infra.persistence.UsersEntity;
import com.mirror.project.infra.repositories.UsersRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsersGatewayImpl implements UsersGateway {

    private final UsersRepository usersRepository;
    private final UsersEntityMapper usersEntityMapper;

    public UsersGatewayImpl(UsersRepository usersRepository, UsersEntityMapper usersEntityMapper) {
        this.usersRepository = usersRepository;
        this.usersEntityMapper = usersEntityMapper;
    }


    @Override
    public Users create(Users user) {
        UsersEntity usersEntity = usersEntityMapper.toEntity(user);
        usersEntity.setId(null);
        UsersEntity savedEntity = usersRepository.save(usersEntity);
        return usersEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .map(usersEntityMapper::toDomain);
    }

    @Override
    public Page<Users> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        var springPage = usersRepository.findAll(pageRequest);

        List<UsersEntity> entities = springPage.getContent();
        long total = springPage.getTotalElements();

        return usersEntityMapper.toDomainPage(entities, page, size, total);
    }
}
