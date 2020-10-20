package com.crims.apps.dao.maintainlog;

import com.crims.apps.model.maintainlog.RecMaintainlog;

import java.util.List;
public interface RecMaintainlogDao {
    List<RecMaintainlog> findAll(RecMaintainlog rec);

    int saveMaintainlog(RecMaintainlog recMaintainlog);

    int deleteMaintainlog(RecMaintainlog recMaintainlog);

    RecMaintainlog findMaintainlogByIndexno(String indexno);

    int updateMaintainlog(RecMaintainlog recMaintainlog);

    int updatescore(RecMaintainlog recMaintainlog);
}
