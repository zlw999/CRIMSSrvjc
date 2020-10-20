package com.crims.apps.service.devicemonitor;

import com.crims.apps.dao.devicemonitor.ConfAlarmthresholdDao;
import com.crims.apps.dao.devicemonitor.ConfMonitorPointDao;
import com.crims.apps.dao.devicemonitor.ConfMpattributeDao;
import com.crims.apps.model.devicemonitor.*;
import com.crims.common.constants.BaseConst;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ConfMonitorPointServiceImpl implements ConfMonitorPointService {

    @Autowired
    private ConfMonitorPointDao confMonitorPointDao;

    @Autowired
    private ConfMpattributeDao confMpattributeDao;

    @Autowired
    private ConfAlarmthresholdDao confAlarmthresholdDao;

    @Override
    @Transactional
    public void addMonitor(MonitorItemBody itemBody, String deviceId) {
        HashMap<String, ArrayList<MonitorItem>> items = itemBody.getItems(); //监控点项
        HashMap<String, MonitorIndexInfo> indexs = itemBody.getIndexs(); //索引项
        ArrayList<ConfMonitorPoint> confMonitorPoints = new ArrayList<>();
        ArrayList<ConfMpattribute> confMpattributes = new ArrayList<>();
        ArrayList<ConfAlarmthreshold> confAlarmthresholds = new ArrayList<>();
        for (String name:indexs.keySet()){
            if(items.containsKey(name)){
                List<String> rowValList = indexs.get(name).getRowVal(); //获取索引项集
                String groupsnStr = null;
                for(int i=0;i<rowValList.size();i++){ //for循环
                    ArrayList<MonitorItem> monitorItems = items.get(name);
                    MonitorItem item1 = monitorItems.get(0);
                    Map<String, String> splitDspMap = this.splitDsp(item1.getItemDsp());
                    if(groupsnStr == null){
                        groupsnStr = splitDspMap.get("groupsn");//获取groupsn
                    }
                    for (MonitorItem item:monitorItems){
                        String typename = item.getTypename();
                        splitDspMap = this.splitDsp(item.getItemDsp());
                        splitDspMap.put("groupsn",groupsnStr);//递增的groupsn重新赋值
                        //监控点
                        ConfMonitorPoint confMonitorPoint = new ConfMonitorPoint();
                        String mpId = composeMpid(item.getType(), groupsnStr, splitDspMap.get("itemsn"), deviceId);
                        String mpIdStr = toUpperCase(mpId);
                        confMonitorPoint.setMpid(mpIdStr);
                        confMonitorPoint.setDeviceid(deviceId);
                        StringBuilder stringBuilder = new StringBuilder();
                        int j = i+1;
                        String typeNameVal = changeTo2(String.valueOf(j));
                        stringBuilder.append(typename).append("-").append(typeNameVal);
                        confMonitorPoint.setMpname(stringBuilder.toString());
                        confMonitorPoint.setMptype(item.getType());
                        confMonitorPoint.setOperateaction(BaseConst.INSERTACTION);
                        confMonitorPoint.setUsestate(1);//使用状态
                        confMonitorPoint.setDsp(mapToStringMonitor(splitDspMap,rowValList.get(i).split("\\|")[0]));
                        confMonitorPoints.add(confMonitorPoint);

                        ///监控点门限attribute
                        ConfMpattribute confMpattribute = new ConfMpattribute();
                        confMpattribute.setDeviceid(deviceId);
                        confMpattribute.setAlarmtype(splitDspMap.get("alarmtype"));
                        confMpattribute.setMpid(mpIdStr);
                        confMpattribute.setMpvaluetype(Integer.parseInt(splitDspMap.get("valuetype")));
                        confMpattribute.setMpvalueunit(splitDspMap.get("unit"));
                        confMpattribute.setValueprecision(Float.parseFloat(splitDspMap.get("precision")));
                        confMpattribute.setMaxeffectvalue(Integer.parseInt(splitDspMap.get("max")));
                        confMpattribute.setMineffectvalue(Integer.parseInt(splitDspMap.get("min")));
                        confMpattribute.setMpmodel(Integer.parseInt(splitDspMap.get("mode")));
                        confMpattribute.setMpcaptureparam(mapToStringMpattr(splitDspMap,rowValList.get(i).split("\\|")[0]));
                        confMpattribute.setOperateaction(BaseConst.INSERTACTION);
                        confMpattributes.add(confMpattribute);
                        //门限
                        List<MonitorThreshold> thresholds = item.getThresholds();
                        for (MonitorThreshold threshold :thresholds){
                            ConfAlarmthreshold confAlarmthreshold = new ConfAlarmthreshold();
                            confAlarmthreshold.setAlarmlevel(threshold.getLevel());
                            confAlarmthreshold.setAlarmsubtype(threshold.getAlarmtype());
                            confAlarmthreshold.setMpid(mpIdStr);
                            confAlarmthreshold.setOperateaction(BaseConst.INSERTACTION);
                            confAlarmthreshold.setDeviceid(deviceId);
                            confAlarmthreshold.setDownvalue(Double.valueOf(threshold.getDownval()));
                            confAlarmthreshold.setUpvalue(Double.valueOf(threshold.getUpval()));
                            confAlarmthresholds.add(confAlarmthreshold);
                        }
                    }
                    int k = Integer.parseInt(groupsnStr);
                    k=k+1;
                    groupsnStr = changeTo2(String.valueOf(k));
                }

            }
        }
        if(!confAlarmthresholds.isEmpty()){
           confAlarmthresholdDao.deleteByDeviceId(deviceId);
           confAlarmthresholdDao.insertList(confAlarmthresholds);
        }
        if(!confMonitorPoints.isEmpty()){
            confMonitorPointDao.deleteByDeviceId(deviceId);
            confMonitorPointDao.insertList(confMonitorPoints);
        }
        if(!confMpattributes.isEmpty()){
            confMpattributeDao.deleteByDeviceId(deviceId);
            confMpattributeDao.insertList(confMpattributes);
        }
    }

    /**
     *组织mpid
     * @param type
     * @param groupsn
     * @param itemsn
     * @param deviceId
     * @return
     */
    public String composeMpid(String type,String groupsn,String itemsn,String deviceId){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(deviceId.substring(0,16));
        stringBuilder.append(type.substring(type.length()-4));
        stringBuilder.append(groupsn);
        stringBuilder.append(itemsn);
        return  stringBuilder.toString();
    }

    public String mapToStringMonitor(Map<String,String> stringMap,String index){
        if(stringMap.isEmpty()){
            return null;
        }
        String gcl = "gcl";
        String groupsn = "groupsn";
        String itemsn = "itemsn";

        ArrayList<String> strings = new ArrayList<>();
        strings.add(gcl);
        strings.add(groupsn);
        strings.add(itemsn);
        StringBuilder stringBuilder = new StringBuilder();
        for(String s :strings){
            String s1 = stringMap.get(s);
            stringBuilder.append(s);
            stringBuilder.append("=");
            stringBuilder.append(s1);
            stringBuilder.append(";");
        }
        stringBuilder.append("ind=").append(index);
        return stringBuilder.toString();
    }

    public String mapToStringMpattr(Map<String,String> stringMap,String index){
        if(stringMap.isEmpty()){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("gcl");
        stringBuilder.append("=");
        stringBuilder.append(stringMap.get("gcl"));
        stringBuilder.append(";");
        stringBuilder.append("ind=").append(index);

        return stringBuilder.toString();
    }

    /**
     * 将hashmap分割成json
     * @param data
     * @return
     */
    public Map<String,String> splitDsp(String data){
        Map map = new HashMap();
        if (null != data) {
            String[] param = data.split(";");
            for (int i = 0; i < param.length; i++) {
                int index = param[i].indexOf('=');
                map.put(param[i].substring(0,index), param[i].substring((index + 1)));
            }
        }
       return map;
    }

    /**
     * 将数字+0
     * @param str
     * @return
     */
    public String changeTo2(String str) {
        if(str.length()>=2){
            return str;
        }
        int length = 2;
        int length1 = str.length();
        if (str != null) {
            if (length1 == length) {
                return str;
            } else {
                for (int i = 0; i < (2 - length1); i++) {
                    str = "0" + str;
                }
                return toUpperCase(str);
            }
        } else {
            return null;
        }

    }

    /**
     * 所有转换大写
     * @param str
     * @return
     */
    public String toUpperCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('a' <= chars[i] && chars[i] <= 'z')
            {
                chars[i] -= 32;
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        ConfMonitorPointServiceImpl confMonitorPointService = new ConfMonitorPointServiceImpl();
        confMonitorPointService.splitDsp("gcl=01;groupsn=01;itemsn=01;alarmtype=0x07020100;valuetype=0x00010B07;unit=0x000A0201;precision=0.1;max=100;min=0;mode=0x00011001");
    }

    @Override
    public PageInfo<ConfMonitorPoint> getMonitorPointList(int currentPage, int pageSize, String deviceId) {
        PageHelper.startPage(currentPage, pageSize);
        List<ConfMonitorPoint> pageList = confMonitorPointDao.getMonitorPointList(deviceId);
        PageInfo<ConfMonitorPoint> pageInfo = new PageInfo<ConfMonitorPoint>(pageList);
        return pageInfo;
    }
}
