package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleInfoDao {

    /**
     * 查询所有角色信息(不分页查询)
     * @return List<ConfRole>对象SON格式输出
     */
    List<ConfRole> getRoleInfoList();

    /**
     * 根据角色名称模糊查询角色信息(分页查询)
     * @param roleName
     * @return List<ConfRole>对象集分页形式以JSON格式输出
     */
    List<ConfRole> getAll(String roleName);

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

    List<String> getRoleInfoByNodeID(String roleid);
}
