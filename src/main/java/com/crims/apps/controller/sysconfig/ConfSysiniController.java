package com.crims.apps.controller.sysconfig;

import com.crims.apps.common.struct.ConfSysini;
import com.crims.container.DataCache;
import com.crims.main.ParamManager;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/confsysini")
public class ConfSysiniController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/getConfsysiniInfo")
    @ResponseBody
    @ApiOperation(value = "获取系统配置参数")
    public List<ConfSysini> getConfsysiniInfo(HttpServletRequest request) {
        try {
            int sequeno = 0;
            File dir = new File("src/main/resources/sysini/sequeno.txt");
            String paths = dir.getCanonicalPath();
            File files = new File(paths);
            BufferedReader reader = null;
            StringBuffer sbf = new StringBuffer();
            try {
                reader = new BufferedReader(new FileReader(files));
                String tempStr;
                while ((tempStr = reader.readLine()) != null) {
                    sbf.append(tempStr);
                }
                sequeno = Integer.parseInt(sbf.toString());
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            String ip = request.getRemoteAddr();
            String sgwsappnodeid = ip + "_" + sequeno;
            List<ConfSysini> confSysinis = new ArrayList<>();
            ConfSysini confSysini = new ConfSysini();
            confSysini.setKey("sgwsappnodeid");
            confSysini.setValue(sgwsappnodeid);
            confSysini.setDsp("带客户端ip信息的应用编号");

            File directory = new File("src/main/resources/sysini/sequeno.txt");
            String path = directory.getCanonicalPath();
            File file = new File(path);
            if(file.exists()){
                file.delete();
            }
            File directoryStr = new File("src/main/resources/sysini");
            String pathStr = directoryStr.getCanonicalPath();
            String fileNewPath = pathStr + "/sequeno.txt";
            File newFile = new File(fileNewPath);
            newFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(fileNewPath);
            sequeno++;
            //开始写
            sgwsappnodeid = ""+sequeno;
            byte[] bytes = sgwsappnodeid.getBytes();
            //将byte数组中的所有数据全部写入
            fos.write(bytes);
            //关闭流
            fos.close();

            ConcurrentHashMap<String,ConfSysini> confSysiniHashMap = DataCache.getInstance().getConfSysiniMap();
            confSysiniHashMap.put(confSysini.getKey(),confSysini);
            for (HashMap.Entry<String, ConfSysini> entry : confSysiniHashMap.entrySet()) {
                ConfSysini confS = entry.getValue();
                confSysinis.add(confS);
            }
            return confSysinis;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/getDevMPDestAppNodeInfo")
    @ResponseBody
    @ApiOperation(value = "获取设备监控点消息地址")
    public HashMap getDevMPDestAppNodeInfo(){
        HashMap map = new HashMap();
        map.put("wsDestNodeId", ParamManager.DevMPConfwsDestNodeId);
        map.put("wsDestDomainId",ParamManager.DevMPConfwsDestDomainId);
        return map;
    }

    @PostMapping("/getDataSubscribeDestAppNodeInfo")
    @ResponseBody
    @ApiOperation(value = "获取数据订阅消息地址")
    public HashMap getDataSubscribeDestAppNodeInfo(){
        HashMap map = new HashMap();
        map.put("wsDestNodeId", ParamManager.DataSubscribewsDestAppNodeId);
        map.put("wsDestDomainId",ParamManager.DataSubscribewsDestDomainId);
        return map;
    }

    @PostMapping("/getDevStatusDestAppNodeInfo")
    @ResponseBody
    @ApiOperation(value = "获取设备状态订阅消息地址")
    public HashMap getDevStatusDestAppNodeInfo(){
        HashMap map = new HashMap();
        map.put("wsDestNodeId", ParamManager.DeviceStatusSubscribewsDestAppNodeId);
        map.put("wsDestDomainId",ParamManager.DeviceStatusSubscribewsDestDomainId);
        return map;
    }

    @PostMapping("/getAlarmSubscribeDestAppNodeInfo")
    @ResponseBody
    @ApiOperation(value = "获取告警订阅消息地址")
    public HashMap getAlarmSubscribeDestAppNodeInfo(){
        HashMap map = new HashMap();
        map.put("wsDestNodeId", ParamManager.AlarmSubscribewsDestAppNodeId);
        map.put("wsDestDomainId",ParamManager.AlarmSubscribewsDestDomainId);
        return map;
    }
}
