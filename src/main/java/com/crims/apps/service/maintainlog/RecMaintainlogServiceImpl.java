package com.crims.apps.service.maintainlog;

import com.crims.apps.dao.maintainlog.RecMaintainlogDao;
import com.crims.apps.model.maintainlog.RecMaintainlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecMaintainlogServiceImpl implements RecMaintainlogService {
    @Autowired
    private RecMaintainlogDao recMaintainlogDao;
    @Override
    public List<RecMaintainlog> findAll(RecMaintainlog rec) {
        return recMaintainlogDao.findAll(rec);
    }

    @Override
    public int saveMaintainlog(RecMaintainlog recMaintainlog) {
        return recMaintainlogDao.saveMaintainlog(recMaintainlog);
    }

    @Override
    public int deleteMaintainlog(RecMaintainlog recMaintainlog) {
        return recMaintainlogDao.deleteMaintainlog(recMaintainlog);
    }

    @Override
    public RecMaintainlog findMaintainlogByIndexno(String indexno) {
        return recMaintainlogDao.findMaintainlogByIndexno(indexno);
    }

    @Override
    public int updateMaintainlog(RecMaintainlog recMaintainlog) {
        return recMaintainlogDao.updateMaintainlog(recMaintainlog);
    }

    @Override
    public int updatescore(RecMaintainlog recMaintainlog) {
        return recMaintainlogDao.updatescore(recMaintainlog);
    }
}
