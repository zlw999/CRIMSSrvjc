package com.crims.apps.controller.maintainlog;

import com.crims.apps.config.CodeMsg;
import com.crims.apps.config.Result;
import com.crims.apps.model.knowledge.ConfKnowledge;
import com.crims.apps.model.knowledge.ConfKnowledgeannex;
import com.crims.apps.model.maintainlog.RecMaintainlog;
import com.crims.apps.model.nettop.ConfEnum;
import com.crims.apps.service.confsysini.ConfSysiniService;
import com.crims.apps.service.knowledge.ConfKnowledgeannexService;
import com.crims.apps.service.maintainlog.RecMaintainlogService;
import com.crims.apps.service.nettop.ConfEnumService;
import com.crims.apps.service.nettop.ConfNodeareaService;
import com.crims.dbconfig.DataSourceContextHolder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("maintainlog")
public class RecMaintainlogController {
    @Autowired
    private RecMaintainlogService recMaintainlogService;
    @Autowired
    private ConfKnowledgeannexService confKnowledgeannexService;
    @Autowired
    private ConfSysiniService confSysiniService;
    @Autowired
    private ConfEnumService enumService;
    @Autowired
    private ConfNodeareaService confNodeareaService;
    //查询所有  分页
    @GetMapping("findAll")
    public Result<PageInfo<RecMaintainlog>> findAll(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                    @RequestParam(required = false, defaultValue = "10") int pageSize, @RequestParam(required = false)String title, @RequestParam(required = false)String maintainuser, @RequestParam(required = false)String nodeid, @RequestParam(required = false)String devicename, @RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")Date start, @RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")Date end){
        DataSourceContextHolder.setKey("crimsrec");
        PageHelper.startPage(pageNum,pageSize);
        RecMaintainlog rec = new RecMaintainlog();
        //通过标题 节点 设备名 用户名 维护时间进行模糊查询
        rec.setTitle(title);
        rec.setDevicename(devicename);
        rec.setStart(start);
        rec.setEnd(end);
        rec.setNodeid(nodeid);
        rec.setMaintainuser(maintainuser);
        List<RecMaintainlog> list = recMaintainlogService.findAll(rec);
        DataSourceContextHolder.setKey("crimsdbs");
        for (RecMaintainlog c: list) {
            String s = deviceType(c.getDevicetype());
            c.setDevice(s);
            RecMaintainlog recMaintainlog = deviceTypeName(c);
            c.setDevicetypename(recMaintainlog.getDevicetypename());
        }
        for (RecMaintainlog recMaintainlog : list) {
            DataSourceContextHolder.setKey("crimsdbs");
            String faulttype = recMaintainlog.getFaulttype();
            String s1 = faulttype.substring(0, 3);
            String item = enumService.findItemValue(s1);
            recMaintainlog.setItem(item);
            String id;
            String s2 = faulttype.substring(3, 5);
            ConfEnum confEnum = new ConfEnum();

            if(s1.equals("001")){
                DataSourceContextHolder.setKey("crimsdbs");
                id="A001";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);
                recMaintainlog.setItemChild(itemChild);
            }else if(s1.equals("002")){
                DataSourceContextHolder.setKey("crimsdbs");
                id="A002";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);
                recMaintainlog.setItemChild(itemChild);

            }else if(s1.equals("003")){
                DataSourceContextHolder.setKey("crimsdbs");
                id="A003";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);
                recMaintainlog.setItemChild(itemChild);

            }else{
                DataSourceContextHolder.setKey("crimsdbs");
                //id="A101";
                /*confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);*/
                recMaintainlog.setItemChild(item);
            }

        }

        for (RecMaintainlog recMaintainlog : list) {
            String deviceid = recMaintainlog.getDeviceid().substring(0, 6);
            //通过前两位查区域
            String s1 = deviceid.substring(0,2);
            String region = confNodeareaService.findNodeNameById(s1);
            recMaintainlog.setRegion(region);
            //通过前四位查线路
            String s2 = deviceid.substring(0,4);
            String line = confNodeareaService.findNodeNameById(s2);
            recMaintainlog.setLine(line);
            //通过前六位查车站
            String s3 = deviceid.substring(0,6);
            String station = confNodeareaService.findNodeNameById(s3);
            recMaintainlog.setStation(station);
        }
        PageInfo<RecMaintainlog> page = new PageInfo<>(list);
        //查询做个判断 如果查出来的数 total=0 就查询失败
       /* if(page.getTotal()==0){
           return Result.error(CodeMsg.SERVER_ERROR);
        }*/
        DataSourceContextHolder.clearKey();
        return Result.success(page);
    }

    //查询所有  不分页
    @GetMapping("getAll")
    public Result<List<RecMaintainlog>> findAll(@RequestParam(required = false)String title,@RequestParam(required = false)String devicename,@RequestParam(required = false)Date start,@RequestParam(required = false)Date end){
        DataSourceContextHolder.setKey("crimsrec");
        RecMaintainlog rec = new RecMaintainlog();
        //通过标题 节点 设备名 用户名 维护时间进行模糊查询
        rec.setTitle(title);
        rec.setDevicename(devicename);
        rec.setStart(start);
        rec.setEnd(end);
        List<RecMaintainlog> list = recMaintainlogService.findAll(rec);
        DataSourceContextHolder.setKey("crimsdbs");
        for (RecMaintainlog c: list) {
            String s = deviceType(c.getDevicetype());
            c.setDevice(s);
            RecMaintainlog recMaintainlog = deviceTypeName(c);
            c.setDevicetypename(recMaintainlog.getDevicetypename());
        }
        for (RecMaintainlog recMaintainlog : list) {
            String faulttype = recMaintainlog.getFaulttype();
            String s1 = faulttype.substring(0, 3);
            String item = enumService.findItemValue(s1);
            recMaintainlog.setItem(item);
            String id;
            String s2 = faulttype.substring(3, 5);
            ConfEnum confEnum = new ConfEnum();

            if(s1.equals("001")){
                id="A001";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);
                recMaintainlog.setItemChild(itemChild);
            }else if(s1.equals("002")){
                id="A002";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);
                recMaintainlog.setItemChild(itemChild);

            }else if(s1.equals("003")){
                id="A003";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);
                recMaintainlog.setItemChild(itemChild);

            }else{
                //id="A101";
                /*confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);*/
                recMaintainlog.setItemChild(item);
            }

        }

        for (RecMaintainlog recMaintainlog : list) {
            String deviceid = recMaintainlog.getDeviceid().substring(0, 6);
            //通过前两位查区域
            String s1 = deviceid.substring(0,2);
            String region = confNodeareaService.findNodeNameById(s1);
            recMaintainlog.setRegion(region);
            //通过前四位查线路
            String s2 = deviceid.substring(0,4);
            String line = confNodeareaService.findNodeNameById(s2);
            recMaintainlog.setLine(line);
            //通过前六位查车站
            String s3 = deviceid.substring(0,6);
            String station = confNodeareaService.findNodeNameById(s3);
            recMaintainlog.setStation(station);
        }
        DataSourceContextHolder.clearKey();
        return Result.success(list);
    }


    //方法
    public RecMaintainlog deviceTypeName(RecMaintainlog recMaintainlog){
        String device = recMaintainlog.getDevicetype();
        if(device.substring(0,1).equals("2")){
            if(device.substring(1,3).equals("01")){
                recMaintainlog.setDevicetypename("固定模拟摄像机(01)");
            }else if(device.substring(1,3).equals("02")){
                recMaintainlog.setDevicetypename("云台模拟摄像机(02)");
            }else if(device.substring(1,3).equals("03")){
                recMaintainlog.setDevicetypename("固定IP摄像机(03)");
            }else{
                recMaintainlog.setDevicetypename("云台IP摄像机(04)");
            }

            return recMaintainlog;
            // ConfDevice.setDeviceType(deviceType);
        }else if (device.equals("00B")){
            String devicename="存储设备";
            recMaintainlog.setDevicetypename(devicename);
            return recMaintainlog;
            //ConfDevice.setDeviceType(deviceType);
        }else if(device.equals("020")){
            String devicename="网络设备";
            recMaintainlog.setDevicetypename(devicename);
            return recMaintainlog;
            //ConfDevice.setDeviceType(deviceType);
        }else {
            String devicename="服务器";
            recMaintainlog.setDevicetypename(devicename);
            return recMaintainlog;
            //ConfDevice.setDeviceType(deviceType);
        }

    }



    //方法
    public String deviceType(String devicetype){
        if(devicetype.substring(0,1).equals("2")){
            String device = "01"+devicetype.substring(1, 3);
            return device;
            // ConfDevice.setDeviceType(deviceType);
        }else if (devicetype.equals("00B")){
            String device="03";
            return device;
            //ConfDevice.setDeviceType(deviceType);
        }else if(devicetype.equals("020")){
            String device="04";
            return device;
            //ConfDevice.setDeviceType(deviceType);
        }else {
            String device="02";
            return device;
            //ConfDevice.setDeviceType(deviceType);
        }

    }

    //添加
    @PostMapping("saveMaintainlog")
    public Result<String> saveMaintainlog(@RequestBody RecMaintainlog recMaintainlog){
        DataSourceContextHolder.setKey("crimsrec");
        String device = recMaintainlog.getDevice();
        String deviecetype = device(device);
        recMaintainlog.setDevicetype(deviecetype);
       int i = recMaintainlogService.saveMaintainlog(recMaintainlog);
       if(i<1){
           return Result.error(CodeMsg.SERVER_ERROR);
       }
        DataSourceContextHolder.clearKey();
       return Result.success("添加成功");
    }

    //方法
    public String device(String deviceType){
        if(deviceType.length()==4){
            String s = deviceType.substring(2, 4);
            String deviceType1="2"+s;
            return deviceType1;
            // ConfDevice.setDeviceType(deviceType);
        }else if (deviceType.equals("03")){
            String deviceType1="00B";
            return deviceType1;
            //ConfDevice.setDeviceType(deviceType);
        }else if(deviceType.equals("04")){
            String deviceType1="020";
            return deviceType1;
            //ConfDevice.setDeviceType(deviceType);
        }else {
            String deviceType1="00C";
            return deviceType1;
            //ConfDevice.setDeviceType(deviceType);
        }

    }

    //测试
    @ApiOperation(value ="上传图片")
    @PostMapping("/addZyFile")
    public Result<String> addZyFile(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        DataSourceContextHolder.setKey("crimsdbs");
        String filename = file.getOriginalFilename();
        System.out.println("上传文件的名称：" + filename);
        String imgpath = confSysiniService.findimgpath();
        String imgpath1 = confSysiniService.findimgpath1();
       // String path = "D:/Dev/apache-tomcat-7.0.70/webapps/img";
        String location = imgpath + "/" + filename;
        System.out.println("上传文件的保存地址：" + location);
        File f = new File(location);

        try {
            f.createNewFile();
            file.transferTo(f);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(imgpath1+filename);
    }

    //测试
    @ApiOperation(value ="上传附件")
    @PostMapping("/uploadFile")
    public Result<String> uploadFile(@RequestParam(value = "file",required = false) MultipartFile file, ConfKnowledgeannex confKnowledgeannex, HttpServletRequest request, HttpServletResponse response) {
        DataSourceContextHolder.setKey("crimsdbs");
        String filename = file.getOriginalFilename();
        System.out.println("上传文件的名称：" + filename);
        String filepath = confSysiniService.findfilepath();
        String filepath1 = confSysiniService.findfilepath1();
        //String path = "D:/Dev/apache-tomcat-7.0.70/webapps/test";
        String documentpath = filepath + "/" + filename;
        System.out.println("上传文件的保存地址：" + documentpath);
        File f = new File(documentpath);

        try {
            f.createNewFile();
            file.transferTo(f);

        } catch (IOException e) {
            e.printStackTrace();
        }

        confKnowledgeannex.setDocumentname(filename);
        confKnowledgeannex.setAnnexno(Integer.parseInt(confKnowledgeannex.getIndexno().substring(22,29)));
        confKnowledgeannex.setDocumentpath(documentpath);
        confKnowledgeannexService.saveConfKnowledgeannex(confKnowledgeannex);
        return Result.success(filepath1+filename);
    }


    //删除
    @PostMapping("deleteMaintainlog")
    public Result<String> deleteMaintainlog(@RequestBody RecMaintainlog recMaintainlog){
        DataSourceContextHolder.setKey("crimsrec");
       int i = recMaintainlogService.deleteMaintainlog(recMaintainlog);
        if(i<1){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        DataSourceContextHolder.clearKey();
        return Result.success("删除成功");
    }

    //修改查询回显  通过id查询对象
    @GetMapping("findMaintainlogByIndexno")
    public Result<RecMaintainlog> findMaintainlogByIndexno(@RequestParam(value = "indexno") String indexno){
        DataSourceContextHolder.setKey("crimsrec");
        /*String documentpath = confKnowledgeannexService.findDocumentpathByIndexno(indexno);
        ConfKnowledge conf =  confKnowledgeService.findConfKnowledgeByIndexno(confKnowledge);
        try {
            String[] split = documentpath.split("/");
            for (int i = 0; i < split.length; i++) {
                conf.setDocumentpath("http://192.168.2.26:8080/test/"+split[split.length-1]);
                conf.setDocumentname(split[split.length-1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("问题不大");
        }*/

       RecMaintainlog recMaintainlog = recMaintainlogService.findMaintainlogByIndexno(indexno);

       if(recMaintainlog==null){
           return Result.error(CodeMsg.SERVER_ERROR);
       }
        String s = deviceType(recMaintainlog.getDevicetype());
        recMaintainlog.setDevice(s);
        DataSourceContextHolder.setKey("crimsdbs");
        String documentpath = confKnowledgeannexService.findDocumentpathByIndexno(indexno);
        try {
            String[] split = documentpath.split("/");
            for (int i = 0; i < split.length; i++) {
                recMaintainlog.setDocumentpath("http://192.168.2.26:8080/test/"+split[split.length-1]);
                recMaintainlog.setDocumentname(split[split.length-1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("问题不大");
        }

        DataSourceContextHolder.clearKey();
       return Result.success(recMaintainlog);
    }

    //修改
    @PostMapping("updateMaintainlog")
    public Result<String> updateMaintainlog(@RequestBody RecMaintainlog recMaintainlog){
        DataSourceContextHolder.setKey("crimsrec");
        int i = recMaintainlogService.updateMaintainlog(recMaintainlog);
        if(i<1){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        DataSourceContextHolder.clearKey();
        return Result.success("修改成功");
    }


    //进行修改评分
    @PostMapping("updatescore")
    public Result<String> updatescore(@RequestBody RecMaintainlog recMaintainlog){
        DataSourceContextHolder.setKey("crimsrec");
        Date date = new Date();
        recMaintainlog.setReviewtime(date);
        int i = recMaintainlogService.updatescore(recMaintainlog);

        if(i<1){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        DataSourceContextHolder.clearKey();
        return Result.success("修改成功");
    }

}
