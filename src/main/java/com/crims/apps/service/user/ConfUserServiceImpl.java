package com.crims.apps.service.user;

import com.crims.apps.dao.confinfo.ConfUserDao;
import com.crims.apps.model.confinfo.ConfUserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfUserServiceImpl implements ConfUserService {

    @Autowired
    private ConfUserDao confUserDao;

    /**
     * 登录验证
     * @param userName 账户名称
     * @param password 密码
     * @return
     */
    @Override
    public ConfUserInfo checkLogin(String userName, String password) {
        return confUserDao.checkLogin(userName,password);
    }

    /**
     * 查询所有用户信息(不分页查询)
     * @return List<ConfUserInfo>对象SON格式输出
     */
    public List<ConfUserInfo> getConfUserList(){
        return confUserDao.getConfUserList();
    }

    /**
     * 根据用户名称模糊查询用户信息(分页查询)
     * @param userName
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfUserInfo>对象集分页形式以JSON格式输出
     */
    @Override
    public PageInfo<ConfUserInfo> getAll(String userName, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        StringBuilder userNameStr = new StringBuilder("%");
        if(userName == null){
            userName = "";
        }
        userNameStr.append(userName);
        userNameStr.append("%");
        List<ConfUserInfo> pageList = confUserDao.getAll(userNameStr.toString());
        PageInfo<ConfUserInfo> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    /**
     * 添加用户信息
     * @param confUserInfo
     * @return int
     */
    public int insertConfUser(ConfUserInfo confUserInfo){
        return confUserDao.insertConfUser(confUserInfo);
    }

    /**
     * 修改用户信息
     * @param confUserInfo
     * @return int
     */
    public int updateConfUser(ConfUserInfo confUserInfo){
        return confUserDao.updateConfUser(confUserInfo);
    }

    /**
     * 删除用户信息
     * @param confUserInfo
     * @return int
     */
    public int deleteConfUser(ConfUserInfo confUserInfo){
        return confUserDao.deleteConfUser(confUserInfo);
    }

    @Override
    public ConfUserInfo getConfUserInfo(String userName, String nodeId) {
        return confUserDao.getConfUserInfo(userName,nodeId);
    }
}
