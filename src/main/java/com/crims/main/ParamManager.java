package com.crims.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParamManager {

    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static ParamManager instance = new ParamManager();
    }

    public static ParamManager newInstance() {
        return SingletonHolder.instance;
    }

    public static String syncsrvnodeid = "ZZZZZZZZZZZZZ";

    public static String DevMPConfwsDestNodeId = "ZZZZZZZZZZZZZ";

    public static String DevMPConfwsDestDomainId = "ZZZZZZZZZZZZZ";

    public static String DataSubscribewsDestAppNodeId = "ZZZZZZZZZZZZZ";

    public static String DataSubscribewsDestDomainId = "ZZZZZZZZZZZZZ";

    public static String DeviceStatusSubscribewsDestAppNodeId = "ZZZZZZZZZZZZZ";

    public static String DeviceStatusSubscribewsDestDomainId = "ZZZZZZZZZZZZZ";

    public static String AlarmSubscribewsDestAppNodeId = "ZZZZZZZZZZZZZ";

    public static String AlarmSubscribewsDestDomainId = "ZZZZZZZZZZZZZ";

    public static String userhbeatinterval = "";

    public static String heartrate = "";

}
