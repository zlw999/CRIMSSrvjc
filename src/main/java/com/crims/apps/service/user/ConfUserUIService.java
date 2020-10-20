package com.crims.apps.service.user;

import com.crims.apps.model.confinfo.ConfUserUI;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ConfUserUIService {

    /**
     * 登录验证
     * @param userId 用户userId
     * @return
     */
    List<ConfUserUI> getConfUserUIList(String userId);

    /**
     * 查询所有用户类型信息
     * @return List<ConfUserUI>对象SON格式输出
     */
    List<ConfUserUI> getConfUserUIInfo();

    /**
     * 查询所有用户类型信息(分页)
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfUserUI>对象集分页形式以JSON格式输出
     */
    PageInfo<ConfUserUI> getAll(int currentPage, int pageSize);

    /**
     * 添加用户类型信息
     * @param confUserUI
     * @return int
     */
    int insertConfUserUI(ConfUserUI confUserUI);

    /**
     * 修改用户类型信息
     * @param confUserUI
     * @return int
     */
    int updateConfUserUI(ConfUserUI confUserUI);

    /**
     * 删除用户类型信息
     * @param confUserUI
     * @return int
     */
    int deleteConfUserUI(ConfUserUI confUserUI);
}
