package com.crims.apps.service.user;

import com.crims.apps.model.user.ConfUser;

import java.util.List;

public interface UserService {
    List<ConfUser> findAll(ConfUser confUser);
}
