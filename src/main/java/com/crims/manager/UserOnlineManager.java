package com.crims.manager;

import com.crims.apps.model.useronline.ConfUserOnline;
import com.crims.common.struct.UserOnlineInfo;
import com.crims.container.DataCache;
import com.crims.main.ParamManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public class UserOnlineManager extends Thread{

    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static UserOnlineManager instance = new UserOnlineManager();
    }

    public static UserOnlineManager getInstance() {
        return SingletonHolder.instance;
    }

    private UserOnlineManager() {}

    private boolean isRunning = true;

    @Override
    public void run() {
        while(isRunning){
            try{

                long secondDiff = Long.valueOf(Integer.parseInt(ParamManager.userhbeatinterval) * Integer.parseInt(ParamManager.heartrate)*1000);

                Date currDate = new Date();

                Map<String, UserOnlineInfo> hashMap = DataCache.getInstance().getUserOnlineMap();

                if (hashMap != null && !hashMap.isEmpty()) {
                    for (Map.Entry<String, UserOnlineInfo> entry : hashMap.entrySet()) {
                        UserOnlineInfo userOnlineInfo = entry.getValue();
                        Date beforeLoginTime = userOnlineInfo.getLongTime();
                        long longTimeInterval = currDate.getTime() - beforeLoginTime.getTime();
                        if(longTimeInterval > secondDiff){//超时，更新用户在线状态
                            userOnlineInfo.setLongTime(currDate);
                            ConfUserOnline confUserOnline = DataCache.getInstance().getConfUserOnlineDao().getUserOnlineInfo(userOnlineInfo.getUserId());
                            if (confUserOnline != null) {
                                confUserOnline.setOnlinestatu("02");//01 在线 02 离线
                                confUserOnline.setLastmodifytime(currDate);
                                DataCache.getInstance().getConfUserOnlineDao().updateUserOnlineInfo(confUserOnline);
                            }
                        }
                    }
                }else{
                    Thread.sleep(10);
                }
                Thread.sleep(10);
            }catch (Exception e){

            }
        }

    }

}
