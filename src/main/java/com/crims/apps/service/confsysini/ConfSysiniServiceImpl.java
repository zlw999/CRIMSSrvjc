package com.crims.apps.service.confsysini;

import com.crims.apps.dao.sysconfig.ConfSysiniDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfSysiniServiceImpl implements ConfSysiniService {
    @Autowired
    private ConfSysiniDao confSysiniDao;

    @Override
    public String findimgpath() {
        return confSysiniDao.findimgpath();
    }

    @Override
    public String findimgpath1() {
        return confSysiniDao.findimgpath1();
    }

    @Override
    public String findfilepath() {
        return confSysiniDao.findfilepath();
    }

    @Override
    public String findfilepath1() {
        return confSysiniDao.findfilepath1();
    }
}
