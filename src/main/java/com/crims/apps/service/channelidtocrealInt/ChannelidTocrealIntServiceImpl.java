package com.crims.apps.service.channelidtocrealInt;

import com.crims.apps.dao.confinfo.ConfChannelidTocrealIntDao;
import com.crims.apps.model.confinfo.ConfChannelidTocrealInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelidTocrealIntServiceImpl implements ChannelidTocrealIntService{

    @Autowired
    private ConfChannelidTocrealIntDao confChannelidTocrealIntDao;

    /**
     * @param confChannelidTocrealInt
     * @return
     */
    @Override
    public int insertConfChannelidTocrealInt(ConfChannelidTocrealInt confChannelidTocrealInt) {
        return confChannelidTocrealIntDao.insertConfChannelidTocrealInt(confChannelidTocrealInt);
    }

    @Override
    public ConfChannelidTocrealInt getConfChannelidTocrealIntInfo(String tb16ID) {
        return confChannelidTocrealIntDao.getConfChannelidTocrealIntInfo(tb16ID);
    }

    @Override
    public int updateChannelidTocrealInt(ConfChannelidTocrealInt confChannelidTocrealInt) {
        return confChannelidTocrealIntDao.updateChannelidTocrealInt(confChannelidTocrealInt);
    }
}
