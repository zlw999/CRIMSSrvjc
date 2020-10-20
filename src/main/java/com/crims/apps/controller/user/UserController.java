package com.crims.apps.controller.user;

import com.crims.apps.model.user.ConfUser;
import com.crims.apps.service.nettop.ConfEnumService;
import com.crims.apps.service.nettop.ConfNodeareaService;
import com.crims.apps.service.user.ConfUserService;
import com.crims.apps.service.user.UserService;
import com.crims.dbconfig.DataSourceContextHolder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin   //解决跨域
@RequestMapping("user")
@Slf4j  //打印日志
public class UserController {

    @Autowired
    private UserService confUserService;
    @Autowired
    private ConfNodeareaService confNodeareaService;
    @Autowired
    private ConfEnumService confEnumService;
    //查询用户表和状态表
    @GetMapping("findUserAndOnline")
    public PageInfo<ConfUser> findAll(
            @RequestParam(required = false)String did,
            @RequestParam(required = false)String username,
            @RequestParam(required = false)String ip,
            @RequestParam(required = false)String usercls,
            @RequestParam(required = false)String onlinestatu,
            @RequestParam(required = false, defaultValue = "1")Integer pageNum, @RequestParam(required = false, defaultValue = "10")Integer pageSize){
        ConfUser confUser = new ConfUser();
        confUser.setUsercls(usercls);
        confUser.setUsername(username);
        String ip1=ip.replace(".","");
        //ip.replaceAll(".","");
        System.out.println(ip1);
        confUser.setIp(ip1);
        confUser.setOnlinestatu(onlinestatu);
        confUser.setDid(did);
        PageHelper.startPage(pageNum,pageSize);
        List<ConfUser> list = confUserService.findAll(confUser);
        //之后通过类型去枚举表找类型 通过状态去枚举表查状态 通过用户id前六位 去查区域线路车站
        for (ConfUser user : list) {
            String userid = user.getUserid();
            String onlinestatu1 = user.getUsercls();
           String name = confEnumService.findItemName(onlinestatu1);
           user.setTypename(name);
            ConfUser u = findSL(userid);
            user.setStation(u.getStation());
            user.setLine(u.getLine());
            user.setRegion(u.getRegion());
        }
        PageInfo<ConfUser> page = new PageInfo<>(list);
        return page;
    }



    //通过id查询车站 区域 列的方法
    public ConfUser findSL(String key){
        DataSourceContextHolder.setKey("crimsdbs");
        ConfUser user = new ConfUser();
        //通过设备id 查询区域 线路 车站
        String deviceid = key.substring(0, 6);
        //通过前两位查区域
        String d1 = deviceid.substring(0,2);
        String region = confNodeareaService.findNodeNameById(d1);
        user.setRegion(region);
        //通过前四位查线路
        String d2 = deviceid.substring(0,4);
        String line = confNodeareaService.findNodeNameById(d2);
        user.setLine(line);
        //通过前六位查车站
        String d3 = deviceid.substring(0,6);
        String station = confNodeareaService.findNodeNameById(d3);
        user.setStation(station);
        DataSourceContextHolder.clearKey();
        return user;
    }

}

