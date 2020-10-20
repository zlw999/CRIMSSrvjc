package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfEnum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DDManDao {

    /**
     * 查询所有记录
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
