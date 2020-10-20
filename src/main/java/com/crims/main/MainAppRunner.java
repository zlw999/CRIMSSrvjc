package com.crims.main;

import com.crims.apps.dao.sysconfig.ConfSysiniDao;
import com.crims.apps.dao.useronline.ConfUserOnlineDao;
import com.crims.container.DataCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component // 被spring容器管理
@Order(1) // 如果多个自定义ApplicationRunner，用来标明执行顺序

public class MainAppRunner implements ApplicationRunner {

	public Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${DevMPConf.wsDestNodeId}")
    private String DevMPConfwsDestNodeId;

    @Value("${DevMPConf.wsDestDomainId}")
    private String DevMPConfwsDestDomainId;

    @Value("${DataSubscribe.wsDestAppNodeId}")
    private String DataSubscribewsDestAppNodeId;

    @Value("${DataSubscribe.wsDestDomainId}")
    private String DataSubscribewsDestDomainId;

    @Value("${DeviceStatusSubscribe.wsDestAppNodeId}")
    private String DeviceStatusSubscribewsDestAppNodeId;

    @Value("${DeviceStatusSubscribe.wsDestDomainId}")
    private String DeviceStatusSubscribewsDestDomainId;

    @Value("${AlarmSubscribe.wsDestAppNodeId}")
    private String AlarmSubscribewsDestAppNodeId;

    @Value("${AlarmSubscribe.wsDestDomainId}")
    private String AlarmSubscribewsDestDomainId;

	@Autowired
    private ConfSysiniDao confSysiniDao;

	@Autowired
    private ConfUserOnlineDao confUserOnlineDao;

	@Override
	public void run(ApplicationArguments args){

        ParamManager.DevMPConfwsDestNodeId = DevMPConfwsDestNodeId;

        ParamManager.DevMPConfwsDestDomainId = DevMPConfwsDestDomainId;

        ParamManager.DataSubscribewsDestAppNodeId = DataSubscribewsDestAppNodeId;

        ParamManager.DataSubscribewsDestDomainId = DataSubscribewsDestDomainId;

        ParamManager.DeviceStatusSubscribewsDestAppNodeId = DeviceStatusSubscribewsDestAppNodeId;

        ParamManager.DeviceStatusSubscribewsDestDomainId = DeviceStatusSubscribewsDestDomainId;

        ParamManager.AlarmSubscribewsDestAppNodeId = AlarmSubscribewsDestAppNodeId;

        ParamManager.AlarmSubscribewsDestDomainId = AlarmSubscribewsDestDomainId;

        DataCache.getInstance().setConfSysiniDao(confSysiniDao);

        DataCache.getInstance().setConfUserOnlineDao(confUserOnlineDao);

		if (MainApp.getInstance().Init()) {
			MainApp.getInstance().start();
		}
	}

}
