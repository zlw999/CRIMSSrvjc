package com.crims.apps.service.nettop;

import com.crims.apps.dao.nettop.ConfEnumDao;
import com.crims.apps.model.nettop.ConfEnum;
import com.crims.apps.service.nettop.ConfEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfEnumServiceImpl implements ConfEnumService {

    @Autowired
    private ConfEnumDao confEnumDao;

    @Override
    public List<ConfEnum> findEnumType() {
        return confEnumDao.findEnumType();
    }

    @Override
    public List<String> findEnumChildType() {
        return confEnumDao.findEnumChildType();
    }

    @Override
    public String findItemValue(String s1) {
        return confEnumDao.findItemValue(s1);
    }

    @Override
    public String findItemChildValue(ConfEnum confEnum) {
        return confEnumDao.findItemChildValue(confEnum);
    }

    @Override
    public String findItemName(String onlinestatu1) {
        return confEnumDao.findItemName(onlinestatu1);
    }
}
