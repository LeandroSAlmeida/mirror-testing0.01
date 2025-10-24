package com.mirror.project.core.usecases;

import com.mirror.project.core.domain.Page;
import com.mirror.project.core.domain.Users;

public interface GetAllUsersUsecase {
    Page<Users> execute(int page, int size);
}
