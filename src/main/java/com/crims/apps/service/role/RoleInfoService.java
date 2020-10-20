package com.crims.apps.service.role;

import com.crims.apps.model.confinfo.ConfRole;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleInfoService {

    /**
     * 查询所有角色信息(不分页查询)
     * @return List<ConfRole>对象SON格式输出
     */
    List<ConfRole> getRoleInfoList();

    /**
     * 根据角色名称模糊查询角色信息(分页查询)
     * @param roleName
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfRole>对象集分页形式以JSON格式输出
     */
    PageInfo<ConfRole> getAll(String roleName,int currentPage, int pageSize);

    /**
     * 添加角色信息
     * @param confRole
     * @return int
     */
    int insertConfRole(ConfRole confRole);

    /**
     * 修改角色信息
     * @param confRole
     * @return int
     */
    int updateConfRole(ConfRole confRole);

    /**
     * 删除角色信息
     * @param confRole
     * @return int
     */
    int deleteConfRole(ConfRole confRole);

    String getRoleInfoByNodeID(String nodeId);
}
