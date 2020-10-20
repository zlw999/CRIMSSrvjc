package com.crims.apps.controller.knowledge;

import com.crims.apps.service.confsysini.ConfSysiniService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.crims.apps.config.Result;
import com.crims.apps.model.nettop.ConfEnum;
import com.crims.apps.model.knowledge.ConfKnowledge;
import com.crims.apps.model.knowledge.ConfKnowledgeannex;
import com.crims.apps.service.nettop.ConfEnumService;
import com.crims.apps.service.knowledge.ConfKnowledgeService;
import com.crims.apps.service.knowledge.ConfKnowledgeannexService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("confknowledge")
public class ConfKnowledgeController {

    @Autowired
    private ConfKnowledgeService confKnowledgeService;
    @Autowired
    private ConfKnowledgeannexService confKnowledgeannexService;
    @Autowired
    private ConfSysiniService confSysiniService;
    @Autowired
    private ConfEnumService enumService;
    //查询所有  加模糊查询
    @ApiOperation(value = "查询所有()",notes = "ConfKnowledge")
    @GetMapping("findAll")
    public Result<PageInfo<ConfKnowledge>> findAll(@RequestParam(required = false)String title,@RequestParam(required = false)String label, @RequestParam(required = false, defaultValue = "1") int pageNum,
                                                             @RequestParam(required = false, defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        ConfKnowledge confKnowledge = new ConfKnowledge();

        try {
            if(label.length()>0) {
            String[] split = label.split(",");
            //StringBuffer buffer = new StringBuffer();
            String l = new String();
            for (int i = 0; i < split.length; i++) {

                //s= s +";"+split[i];
                l+=";"+split[i];

            }
            System.out.println(l);
            confKnowledge.setLabel(l);
            }
        } catch (Exception e) {
            System.out.println("问题不大");
        }
        confKnowledge.setTitle(title);
        List<ConfKnowledge> list = confKnowledgeService.findAll(confKnowledge);
        for (ConfKnowledge c: list) {
            String s = deviceType(c.getDevicetype());
            c.setDevice(s);
            ConfKnowledge confKnowledge1 = deviceTypeName(c);
            c.setDevicename(confKnowledge1.getDevicename());
        }
        for (ConfKnowledge knowledge : list) {
            String faulttype = knowledge.getFaulttype();
            String s1 = faulttype.substring(0, 3);
            String item = enumService.findItemValue(s1);
            knowledge.setItem(item);
            String id;
            String s2 = faulttype.substring(3, 5);
            ConfEnum confEnum = new ConfEnum();

            if(s1.equals("001")){
                 id="A001";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
               String itemChild = enumService.findItemChildValue(confEnum);
                knowledge.setItemChild(itemChild);
            }else if(s1.equals("002")){
                id="A002";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);
                knowledge.setItemChild(itemChild);

            }else if(s1.equals("003")){
                id="A003";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);
                knowledge.setItemChild(itemChild);

            }else{
                //id="A101";
                /*confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);*/
                knowledge.setItemChild(item);
            }

        }
        PageInfo<ConfKnowledge> page = new PageInfo<>(list);
        return Result.success(page);
    }

    /*@GetMapping("findByLike")
    public Result<PageInfo<ConfKnowledge>> findByLike(@RequestParam(value = "title")String title,@RequestParam(required = false, defaultValue = "1") int pageNum,
    @RequestParam(required = false, defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ConfKnowledge> list = confKnowledgeService.findAll(confKnowledge);
        for (ConfKnowledge c: list) {
            String s = deviceType(c.getDevicetype());
            c.setDevice(s);
            ConfKnowledge confKnowledge1 = deviceTypeName(c);
            c.setDevicename(confKnowledge1.getDevicename());
        }
        for (ConfKnowledge knowledge : list) {
            String faulttype = knowledge.getFaulttype();
            String s1 = faulttype.substring(0, 3);
            String item = enumService.findItemValue(s1);
            knowledge.setItem(item);
            String id="";
            String s2 = faulttype.substring(3, 5);
            ConfEnum confEnum = new ConfEnum();

            if(s1.equals("001")){
                id="A001";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);
                knowledge.setItemChild(itemChild);
            }else if(s1.equals("002")){
                id="A002";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);
                knowledge.setItemChild(itemChild);

            }else if(s1.equals("003")){
                id="A003";
                confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);
                knowledge.setItemChild(itemChild);

            }else{
                //id="A101";
                *//*confEnum.setId(id);
                confEnum.setItemValue(s2);
                String itemChild = enumService.findItemChildValue(confEnum);*//*
                knowledge.setItemChild(item);
            }

        }
        PageInfo<ConfKnowledge> page = new PageInfo<>(list);
        return Result.success(page);
    }*/
    //添加方法
    @PostMapping("saveConfKnowledge")
    @ApiOperation(value = "添加()",notes = "ConfKnowledge")
    public Result<String> saveConfKnowledge( @RequestBody ConfKnowledge confKnowledge){

     /* if(confKnowledge.getHasDoc()==1){
          String deviecetype = device(devicetype);
          confKnowledge.setDevicetype(deviecetype);
          String filename = file.getOriginalFilename();
        System.out.println("上传文件的名称：" + filename);
        String path = "D:/Knowledge";
        String documentpath = path + "/" + filename;
        System.out.println("上传文件的保存地址：" + documentpath);
        File f = new File(documentpath);

        try {
            f.createNewFile();
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //confKnowledge.setIndexno("6");
       // confKnowledgeannex.setIndexno("4");
                confKnowledgeannex.setIndexno(confKnowledge.getIndexno());

        confKnowledgeannex.setAnnexno(Integer.parseInt(confKnowledge.getIndexno().substring(22,29)));
        confKnowledgeannex.setDocumentpath(documentpath);
       confKnowledgeannexService.saveConfKnowledgeannex(confKnowledgeannex);
        confKnowledgeService.saveConfKnowledge(confKnowledge);
           return Result.success("添加成功");
      }*/
        String device = confKnowledge.getDevice();
        String deviecetype = device(device);
        confKnowledge.setDevicetype(deviecetype);
        confKnowledge.setReviewstatus(1);
        String[] split = confKnowledge.getLabel().split(",");
        //StringBuffer buffer = new StringBuffer();
        String s = new String();
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
           //s= s +";"+split[i];
             s+=";"+split[i];
        }
        confKnowledge.setLabel(s);
        confKnowledgeService.saveConfKnowledge(confKnowledge);
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

    //方法
    public ConfKnowledge deviceTypeName(ConfKnowledge confKnowledge){
        String device = confKnowledge.getDevicetype();
        if(device.substring(0,1).equals("2")){
            if(device.substring(1,3).equals("01")){
                confKnowledge.setDevicename("固定模拟摄像机(01)");
            }else if(device.substring(1,3).equals("02")){
                confKnowledge.setDevicename("云台模拟摄像机(02)");
            }else if(device.substring(1,3).equals("03")){
                confKnowledge.setDevicename("固定IP摄像机(03)");
            }else{
                confKnowledge.setDevicename("云台IP摄像机(04)");
            }

            return confKnowledge;
            // ConfDevice.setDeviceType(deviceType);
        }else if (device.equals("00B")){
            String devicename="存储设备";
            confKnowledge.setDevicename(devicename);
            return confKnowledge;
            //ConfDevice.setDeviceType(deviceType);
        }else if(device.equals("020")){
            String devicename="网络设备";
            confKnowledge.setDevicename(devicename);
            return confKnowledge;
            //ConfDevice.setDeviceType(deviceType);
        }else {
            String devicename="服务器";
            confKnowledge.setDevicename(devicename);
            return confKnowledge;
            //ConfDevice.setDeviceType(deviceType);
        }

    }

    //测试
    @ApiOperation(value ="上传图片")
    @PostMapping("/addZyFile")
    public Result<String> addZyFile(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

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


    //修改上传附件的方法


    //删除方法
    @PostMapping("deleteConfKnowledge")
    @ApiOperation(value = "删除()",notes = "ConfKnowledge")
    public Result<String> deleteConfKnowledge(@RequestBody ConfKnowledge confKnowledge){
        confKnowledgeService.deleteConfKnowledge(confKnowledge);
        return Result.success("删除成功");
    }
    //通过id查询 进行修改的回显
    @GetMapping("findConfKnowledgeByIndexno")
    @ApiOperation(value = "修改回显()",notes = "ConfKnowledge")
    public Result<ConfKnowledge> findConfKnowledgeByIndexno(ConfKnowledge confKnowledge){
        String indexno = confKnowledge.getIndexno();
        String documentpath = confKnowledgeannexService.findDocumentpathByIndexno(indexno);
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
        }
        String s = deviceType(conf.getDevicetype());
        conf.setDevice(s);
        return Result.success(conf);
    }
    //进行修改
    @PostMapping("updateConfKnowledge")
    public Result<String> updateConfKnowledge(@RequestBody ConfKnowledge confKnowledge){
        String device = device(confKnowledge.getDevicetype());
        confKnowledge.setDevicetype(device);
        confKnowledgeService.updateConfKnowledge(confKnowledge);
        return Result.success("修改成功");
    }

    //审核之后进行修改 增加审核人 审核时间 审核人id
    @PostMapping("shenheConfKnowledge")
    public Result<String> shenheConfKnowledge(@RequestBody ConfKnowledge confKnowledge){
        Date date = new Date();
        confKnowledge.setReviewtime(date);
        confKnowledgeService.shenheConfKnowledge(confKnowledge);
        return Result.success("审核提交成功");
    }
    //获取附件类型


    //查询状态
}
