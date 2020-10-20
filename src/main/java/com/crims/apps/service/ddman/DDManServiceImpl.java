package com.crims.apps.service.ddman;

import com.crims.apps.dao.confinfo.DDManDao;
import com.crims.apps.model.confinfo.ConfEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DDManServiceImpl implements DDManService{

    @Autowired
    private DDManDao ddManDao;

    /**
     * 查询所有字典信息(分页)
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfEnum>对象集分页形式以JSON格式输出
     */
    @Override
    public PageInfo<ConfEnum> getAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<ConfEnum> pageList = ddManDao.getConfEnumList();
        PageInfo<ConfEnum> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    /**
     * 查询所有字典信息(不分页)
     * @return List<ConfEnum>对象SON格式输出
     */
    @Override
    public List<ConfEnum> getConfEnumList(){
        return ddManDao.getConfEnumList();
    }

    /**
     * 查询某项下所有子项信息
     * @param id
     * @return ConfEnum对象转JSON格式输出
     */
    @Override
    public List<ConfEnum> getId(String id) {
        return ddManDao.getId(id);
    }

    /**
     * 查询某子项信息
     * @param item_value
     * @param confEnumID
     * @return ConfEnum对象转JSON格式输出
     */
    @Override
    public ConfEnum getItem_value(String item_value,String confEnumID) {
        return ddManDao.getItem_value(item_value,confEnumID);
    }
}
