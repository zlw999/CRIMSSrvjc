package com.crims.apps.dao.user;

import com.crims.apps.model.user.ConfUser;

import java.util.List;

public interface UserDao {
    List<ConfUser> findAll(ConfUser confUser);
}
