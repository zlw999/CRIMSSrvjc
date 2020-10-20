package com.crims.apps.service.nettop;

import com.crims.apps.model.nettop.ConfNettop;

import java.util.List;

public interface ConfNettopService {
    List<ConfNettop> findAll(ConfNettop confNettop);

    void insert(ConfNettop confNettop);

    void delete(int topid);

    void update(ConfNettop confNettop);

    ConfNettop findConfnettopByTopid(int i);

    List<ConfNettop> selectByParentId(int topid);

    ConfNettop findConfnettopByTopidByParentId(int parentid);

    ConfNettop findConfnettopById(int id);

    void updateByTopid(int t1, int topidOld);

    List<ConfNettop> findConfnettopByParentId(ConfNettop confNettop);


    void updateConfNettopWithId(ConfNettop confNettop);

    ConfNettop findConfNettopById(ConfNettop confNettop);

    void update1(ConfNettop confNettop);
}
