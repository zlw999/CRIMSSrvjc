package com.crims.apps.service.user;

import com.crims.apps.dao.user.UserDao;
import com.crims.apps.model.user.ConfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<ConfUser> findAll(ConfUser confUser) {
        return userDao.findAll(confUser);
    }
}
