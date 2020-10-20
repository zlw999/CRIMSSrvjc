package com.crims.container;

import com.crims.apps.common.struct.ConfSysini;
import com.crims.apps.dao.sysconfig.ConfSysiniDao;
import com.crims.apps.dao.useronline.ConfUserOnlineDao;
import com.crims.common.struct.UserOnlineInfo;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DataCache {

    private static class SingletonHolder {
        public static DataCache instance = new DataCache();
    }

    private DataCache() {}

    public static DataCache getInstance() {
        return SingletonHolder.instance;
    }

    ConcurrentHashMap<String, ConfSysini> confSysiniMap = new ConcurrentHashMap<>();

    ConcurrentHashMap<String, UserOnlineInfo> userOnlineMap = new ConcurrentHashMap<>();

    private ConfSysiniDao confSysiniDao;

    private ConfUserOnlineDao confUserOnlineDao;

    public ConfSysiniDao getConfSysiniDao() {
        return confSysiniDao;
    }

    public void setConfSysiniDao(ConfSysiniDao confSysiniDao) {
        this.confSysiniDao = confSysiniDao;
    }

    public ConfUserOnlineDao getConfUserOnlineDao() {
        return confUserOnlineDao;
    }

    public void setConfUserOnlineDao(ConfUserOnlineDao confUserOnlineDao) {
        this.confUserOnlineDao = confUserOnlineDao;
    }

    public boolean addConfSysiniInfo(List<ConfSysini> confSysiniList){
        if (confSysiniList != null && !confSysiniList.isEmpty()) {
            Iterator<ConfSysini> confSysinis = confSysiniList.iterator();
            while (confSysinis.hasNext()) {
                ConfSysini confSysini = confSysinis.next();
                this.confSysiniMap.put(confSysini.getKey(),confSysini);
            }
        }
        return true;
    }

    public ConcurrentHashMap getConfSysiniMap(){
        return this.confSysiniMap;
    }

    public boolean addUserOnlineMap(UserOnlineInfo userOnlineInfo){
        if (userOnlineInfo != null) {
            this.userOnlineMap.put(userOnlineInfo.getUserId(),userOnlineInfo);
        }

        return true;
    }

    public ConcurrentHashMap getUserOnlineMap(){
        if (this.userOnlineMap != null && !this.userOnlineMap.isEmpty()) {
            return this.userOnlineMap;
        }

        return null;
    }
}
