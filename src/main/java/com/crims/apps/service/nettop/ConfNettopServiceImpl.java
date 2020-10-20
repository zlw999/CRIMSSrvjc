package com.crims.apps.service.nettop;

import com.crims.apps.dao.nettop.ConfNettopDao;
import com.crims.apps.model.nettop.ConfNettop;
import com.crims.apps.model.nettop.ConfNettop;
import com.crims.apps.service.nettop.ConfNettopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfNettopServiceImpl implements ConfNettopService {

    @Autowired
    private ConfNettopDao confNettopDao;



    @Override
    public List<ConfNettop> findAll(ConfNettop confNettop) {

        return confNettopDao.findAll(confNettop);
    }

    @Override
    public void insert(ConfNettop confNettop) {
        confNettopDao.insert(confNettop);
    }

    @Override
    public void delete(int topid) {
        confNettopDao.delete(topid);
    }

    @Override
    public void update(ConfNettop confNettop) {
        confNettopDao.update(confNettop);
    }

    @Override
    public ConfNettop findConfnettopByTopid(int topid) {
        return confNettopDao.findConfnettopByTopid(topid);
    }

    @Override
    public List<ConfNettop> selectByParentId(int topid) {
        return confNettopDao.selectByParentId(topid);
    }

    @Override
    public ConfNettop findConfnettopByTopidByParentId(int parentid) {
        return confNettopDao.findConfnettopByTopidByParentId(parentid);
    }

    @Override
    public ConfNettop findConfnettopById(int id) {
        return confNettopDao.findConfnettopById(id);
    }

    @Override
    public void updateByTopid(int t1, int topidOld) {
        confNettopDao.updateByTopid(t1,topidOld);
    }

    @Override
    public List<ConfNettop> findConfnettopByParentId(ConfNettop confNettop) {
        return confNettopDao.findConfnettopByParentId(confNettop);
    }

    @Override
    public void updateConfNettopWithId(ConfNettop confNettop) {
        confNettopDao.updateConfNettopWithId(confNettop);
    }

    @Override
    public ConfNettop findConfNettopById(ConfNettop confNettop) {
        return confNettopDao.findConfNettopById(confNettop);
    }

    @Override
    public void update1(ConfNettop confNettop) {
        confNettopDao.update1(confNettop);
    }


}
