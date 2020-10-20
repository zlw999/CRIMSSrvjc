package com.crims.main;

import com.crims.apps.common.struct.ConfSysini;
import com.crims.container.DataCache;
import com.crims.manager.UserOnlineManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MainApp extends Thread {

	public Logger logger = LoggerFactory.getLogger(getClass());

	private static class SingletonHolder {
		public static MainApp instance = new MainApp();
	}

	public static MainApp getInstance() {
		return SingletonHolder.instance;
	}

	private MainApp() {
		this.setName("CCSP_MainApp");
	}

	public boolean Init() {
		return true;
	}

	@Override
	public void run() {

		do {

		    //获取系统配置
            List<ConfSysini> confSysiniList = DataCache.getInstance().getConfSysiniDao().getConfSysini();

            if(confSysiniList != null && !confSysiniList.isEmpty()){

                for (int i = 0; i < confSysiniList.size(); i++) {
                    ConfSysini confSysini = confSysiniList.get(i);
                    if("syncsrvnodeid".equals(confSysini.getKey())){
                        ParamManager.syncsrvnodeid = confSysini.getValue();
                    }
                    if("userhbeatinterval".equals(confSysini.getKey())){
                        ParamManager.userhbeatinterval = confSysini.getValue();
                    }
                    if("heartrate".equals(confSysini.getKey())){
                        ParamManager.heartrate = confSysini.getValue();
                    }
                }
            }

            DataCache.getInstance().addConfSysiniInfo(confSysiniList);

            // 1.初始化
			logger.info("...开始初始化...");

            UserOnlineManager.getInstance().start();

		} while (false);
	}

}
