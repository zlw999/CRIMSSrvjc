package com.crims.apps.service.user;


import com.crims.apps.model.confinfo.ConfUserInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ConfUserService {

    /**
     * 登录验证
     * @param userName 账户名称
     * @param password 密码
     * @return
     */
    ConfUserInfo checkLogin(String userName, String password);

    /**
     * 查询所有用户信息(不分页查询)
     * @return List<ConfUserInfo>对象SON格式输出
     */
    List<ConfUserInfo> getConfUserList();

    /**
     * 根据用户名称模糊查询用户信息(分页查询)
     * @param userName
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfUserInfo>对象集分页形式以JSON格式输出
     */
    PageInfo<ConfUserInfo> getAll(String userName, int currentPage, int pageSize);

    /**
     * 添加用户信息
     * @param confUserInfo
     * @return int
     */
    int insertConfUser(ConfUserInfo confUserInfo);

    /**
     * 修改用户信息
     * @param confUserInfo
     * @return int
     */
    int updateConfUser(ConfUserInfo confUserInfo);

    /**
     * 删除用户信息
     * @param confUserInfo
     * @return int
     */
    int deleteConfUser(ConfUserInfo confUserInfo);

    ConfUserInfo getConfUserInfo(String userName,String nodeId);

}
