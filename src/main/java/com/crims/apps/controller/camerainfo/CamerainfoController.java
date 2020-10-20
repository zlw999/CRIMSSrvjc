package com.crims.apps.controller.camerainfo;

import com.crims.apps.config.Result;
import com.crims.apps.service.camerainfo.CamerainfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin   //解决跨域
@RequestMapping("confenum")
@Slf4j  //打印日志
public class CamerainfoController {
    @Autowired
    private CamerainfoService confCamerainfoService;
    //传过来的进行修改  设备id 设备name 新加的字段名 字段值
    /*@PostMapping("update")
    public Result update(@RequestParam(required = false)String deviceid,@RequestParam(required = false)String devicename,@RequestParam(required = false)String zname,@RequestParam(required = false)String zvalue){
        ConfCamerainfo c = new ConfCamerainfo();
        c.setDeviceid(deviceid);
        c.setDevicename(devicename);
        c.setZname(zname);
        c.setZvalue(zvalue);
       int i = confCamerainfoService.update(c);
        if(i==0){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        return Result.success("修改成功");
    }*/


    //查询设备属性的接口
    @GetMapping("findAll")
    public Result<Map> findAll(@RequestParam(required = false)String deviceid){
       // List<String> listValue = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        List<String> listType = confCamerainfoService.findType();
        for (String s : listType) {
            //通过id 查询表数据
             String value = confCamerainfoService.findValueById(deviceid,s);
            //listValue.add(value);
            map.put(s,value);
        }
      /* for (String key : map.keySet()) {
              if(map.get("deviceid")==null){
                  return Result.error(CodeMsg.SERVER_ERROR);
              }
              }*/
        return Result.success(map);
    }

    //修改数据的接口
    @PostMapping("update")
    @ApiOperation(value = "not use")
    @ApiImplicitParam(name = "map" , paramType = "body",examples = @Example({
            @ExampleProperty(value = "{'user':'id'}", mediaType = "application/json")
    }))
    public Result update(@RequestParam(required = false) Map<String,Object> map){
        for (String type : map.keySet()) {
            System.out.println("key= "+ type + " and value= " + map.get(type));
            String deviceid = (String) map.get("deviceid");
            String value = (String) map.get(type);
            if (value == "") {
                value=null;
            }
            //通过设备id 判断这个设备是否存在
            String isExist = confCamerainfoService.find(deviceid);
            //如果存在 直接修改  如果不存在 需要先添加一个只有deviceid的数据 再修改
            if(isExist!=null){
                confCamerainfoService.updateConf(type,value,deviceid);
            }else{
                String val = null;
                confCamerainfoService.addConf(type,val,deviceid);
                confCamerainfoService.updateConf(type,value,deviceid);
            }

            //confCamerainfoService.deleteConf(deviceid);
            //confCamerainfoService.addConf(type,value,deviceid);
           // confCamerainfoService.updateConf(type,value,deviceid);
        }
          return Result.success("修改成功");

    }


}
