package com.crims.apps.controller.nettop;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.crims.apps.config.Result;
import com.crims.apps.model.nettop.ConfDevice;
import com.crims.apps.model.nettop.ConfEnum;
import com.crims.apps.model.nettop.ConfNodearea;
import com.crims.apps.service.nettop.ConfDeviceService;
import com.crims.apps.service.nettop.ConfEnumService;
import com.crims.apps.service.nettop.ConfNodeareaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin   //解决跨域
@RequestMapping("confnodearea")
@Slf4j  //打印日志
public class ConfNodeareaController {

    @Autowired
    private ConfNodeareaService confNodeareaService;
    @Autowired
    private ConfEnumService confEnumService;
    @Autowired
    private ConfDeviceService confDeviceService;
    //查询所有
    @GetMapping("findAll")
    public Result<List<ConfNodearea>> findAll(){
      List<ConfNodearea> list = confNodeareaService.findAll();
        for (ConfNodearea confNodearea : list) {
            if(confNodearea.getNodeid().length()==4){
                String nodeidnew=confNodearea.getNodeid().substring(0,2);
               String nodenamenew = confNodeareaService.findNodeNameById(nodeidnew);
              String nodename = nodenamenew+"-"+confNodearea.getNodename();
              confNodearea.setNodename(nodename);
            }

        }
      /* for (ConfNodearea confNodearea : list) {
            if(confNodearea.getNodeid().length()==2){
                list.remove(confNodearea);
            }
        }*/
        for (int i = 0; i < list.size(); i++) {
           if(list.get(i).getNodeid().length()==2) {
               list.remove(i);
           }
        }
      return Result.success(list);
    }

    //查询所有设备主类型
    @GetMapping("findEnumType")
    public Result<List<ConfEnum>> findEnumType(){
       List<ConfEnum> list = confEnumService.findEnumType();
        return Result.success(list);
    }

    //查询摄像机下面的子类型
    @GetMapping("findEnumChildType")
    public Result<List<String>> findEnumChildType(){
       List<String> list = confEnumService.findEnumChildType();
        return Result.success(list);
    }

    //查询设备
    @GetMapping("findDevice")
    public Result<PageInfo<ConfDevice>> findDevice(@RequestParam(value = "deviceid") String deviceid
            ,@RequestParam(value = "deviceType") String deviceType,@RequestParam(value = "pageNum")Integer pageNum,@RequestParam(value = "pageSize")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        /*confDevice.setDeviceType(deviceType);
        confDevice.setDeviceid(deviceid);*/
        //@RequestBody ConfDevice ConfDevice
       // String did = ConfDevice.getDeviceType();
        if(deviceType.length()==4){
            String s = deviceType.substring(2, 4);
            String deviceType1="2"+s;
            ConfDevice confDevice = new ConfDevice();
            confDevice.setDeviceType(deviceType1);
            confDevice.setDeviceid(deviceid);
            //再根据类型 查出设备
            List<ConfDevice> list = confDeviceService.findDevice(confDevice);
            PageInfo<ConfDevice> page = new PageInfo<>(list);
            return Result.success(page);
           // ConfDevice.setDeviceType(deviceType);
        }else if (deviceType.equals("03")){
            String deviceType1="00B";
            ConfDevice confDevice = new ConfDevice();
            confDevice.setDeviceType(deviceType1);
            confDevice.setDeviceid(deviceid);
            //再根据类型 查出设备
            List<ConfDevice> list = confDeviceService.findDevice(confDevice);
            PageInfo<ConfDevice> page = new PageInfo<>(list);
            return Result.success(page);
            //ConfDevice.setDeviceType(deviceType);
        }else if(deviceType.equals("04")){
            String deviceType1="020";
            ConfDevice confDevice = new ConfDevice();
            confDevice.setDeviceType(deviceType1);
            confDevice.setDeviceid(deviceid);
            //再根据类型 查出设备
            List<ConfDevice> list = confDeviceService.findDevice(confDevice);
            PageInfo<ConfDevice> page = new PageInfo<>(list);
            return Result.success(page);
            //ConfDevice.setDeviceType(deviceType);
        }else {
            String deviceType1="00C";
            ConfDevice confDevice = new ConfDevice();
            confDevice.setDeviceType(deviceType1);
            confDevice.setDeviceid(deviceid);
            //再根据类型 查出设备
            List<ConfDevice> list = confDeviceService.findDevice(confDevice);
            PageInfo<ConfDevice> page = new PageInfo<>(list);
            return Result.success(page);
            //ConfDevice.setDeviceType(deviceType);
        }




       // return null;
    }




}
