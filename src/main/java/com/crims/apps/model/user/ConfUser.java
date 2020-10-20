package com.crims.apps.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConfUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String roleid;
    private String userid;
    private String username;
    private String password;
    private Integer priorlevel;
    private String unitid;
    private String position;
    private String telephone;
    private String mobile;
    private String address;
    private Integer usestate;
    private Integer operateaction;
    private String operateuserid;
    private String operateusername;
    private String lastModifyTime;
    private String ip;
    private String nodeid;
    private String usercls;
    private String onlinestatu;
    //区域
    private String region;
    //线路
    private String line;
    //车站
    private String station;
    private String did;
    private String typename;
}
