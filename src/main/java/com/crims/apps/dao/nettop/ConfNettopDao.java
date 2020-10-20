package com.crims.apps.dao.nettop;

import com.crims.apps.model.nettop.ConfNettop;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConfNettopDao {
    List<ConfNettop> findAll(ConfNettop confNettop);

    void insert(ConfNettop confNettop);

    void delete(int topid);

    void update(ConfNettop confNettop);

    ConfNettop findConfnettopByTopid(int topid);

    List<ConfNettop> selectByParentId(int topid);

    ConfNettop findConfnettopByTopidByParentId(int parentid);

    ConfNettop findConfnettopById(int id);

    void updateByTopid(int t1, int topidOld);

    List<ConfNettop> findConfnettopByParentId(ConfNettop confNettop);

    void insertConfNettopWithId(ConfNettop confNettop);

    void updateConfNettopWithId(ConfNettop confNettop);

    ConfNettop findConfNettopById(ConfNettop confNettop);

    void update1(ConfNettop confNettop);
}
