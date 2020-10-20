package com.crims.apps.service.user;

import com.crims.apps.dao.confinfo.ConfUserUIDao;
import com.crims.apps.model.confinfo.ConfUserUI;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfUserUIServiceImpl implements ConfUserUIService {

    @Autowired
    private ConfUserUIDao confUserUIDao;

    /**
     * @param userId 用户userId
     * @return
     */
    @Override
    public List<ConfUserUI> getConfUserUIList(String userId) {
        return confUserUIDao.getConfUserUIList(userId);
    }

    /**
     * 查询所有用户类型信息
     * @return List<ConfUserUI>对象SON格式输出
     */
    public List<ConfUserUI> getConfUserUIInfo(){
        return confUserUIDao.getConfUserUIInfo();
    }

    /**
     * 查询所有用户类型信息(分页)
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfUserUI>对象集分页形式以JSON格式输出
     */
    @Override
    public PageInfo<ConfUserUI> getAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<ConfUserUI> pageList = confUserUIDao.getConfUserUIInfo();
        PageInfo<ConfUserUI> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    /**
     * 添加用户类型信息
     * @param confUserUI
     * @return int
     */
    public int insertConfUserUI(ConfUserUI confUserUI){
        return confUserUIDao.insertConfUserUI(confUserUI);
    }

    /**
     * 修改用户类型信息
     * @param confUserUI
     * @return int
     */
    public int updateConfUserUI(ConfUserUI confUserUI){
        return confUserUIDao.updateConfUserUI(confUserUI);
    }

    /**
     * 删除用户类型信息
     * @param confUserUI
     * @return int
     */
    public int deleteConfUserUI(ConfUserUI confUserUI){
        return confUserUIDao.deleteConfUserUI(confUserUI);
    }
}
