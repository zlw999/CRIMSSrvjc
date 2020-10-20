package com.crims.apps.service.ddman;

import com.crims.apps.model.confinfo.ConfEnum;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DDManService {

    /**
     * 查询所有字典信息(分页)
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfEnum>对象集分页形式以JSON格式输出
     */
    PageInfo<ConfEnum> getAll(int currentPage, int pageSize);

    /**
     * 查询所有字典信息(不分页)
     * @return List<ConfEnum>对象SON格式输出
     */
    List<ConfEnum> getConfEnumList();

    /**
     * 查询某项下所有子项信息
     * @param id
     * @return ConfEnum对象转JSON格式输出
     */
     List<ConfEnum> getId(String id);

    /**
     * 查询某子项信息
     * @param item_value
     * @param confEnumID
     * @return ConfEnum对象转JSON格式输出
     */
    ConfEnum getItem_value(String item_value,String confEnumID);
}
