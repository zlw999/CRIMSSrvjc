package com.crims.apps.service.role;

import com.crims.apps.dao.confinfo.RoleInfoDao;
import com.crims.apps.model.confinfo.ConfRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleInfoServiceImpl implements RoleInfoService{

    @Autowired
    private RoleInfoDao roleInfoDao;

    /**
     * 查询所有角色信息(不分页查询)
     * @return List<ConfRole>对象SON格式输出
     */
    public List<ConfRole> getRoleInfoList(){
        return roleInfoDao.getRoleInfoList();
    }

    /**
     * 根据角色名称模糊查询角色信息(分页查询)
     * @param roleName
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfRole>对象集分页形式以JSON格式输出
     */
    @Override
    public PageInfo<ConfRole> getAll(String roleName, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        StringBuilder roleNameStr = new StringBuilder("%");
        if(roleName == null){
            roleName = "";
        }
        roleNameStr.append(roleName);
        roleNameStr.append("%");
        List<ConfRole> pageList = roleInfoDao.getAll(roleNameStr.toString());
        PageInfo<ConfRole> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    /**
     * 添加角色信息
     * @param confRole
     * @return int
     */
    public int insertConfRole(ConfRole confRole){
        return roleInfoDao.insertConfRole(confRole);
    }

    /**
     * 修改角色信息
     * @param confRole
     * @return int
     */
    public int updateConfRole(ConfRole confRole){
        return roleInfoDao.updateConfRole(confRole);
    }

    /**
     * 删除角色信息
     * @param confRole
     * @return int
     */
    public int deleteConfRole(ConfRole confRole){
        return roleInfoDao.deleteConfRole(confRole);
    }

    @Override
    public String getRoleInfoByNodeID(String nodeId) {
        List<String> list =  roleInfoDao.getRoleInfoByNodeID(nodeId);
        String roleId = "";
        if (list != null && !list.isEmpty()) {
            roleId = list.get(0);
        }
        return roleId;
    }
}
