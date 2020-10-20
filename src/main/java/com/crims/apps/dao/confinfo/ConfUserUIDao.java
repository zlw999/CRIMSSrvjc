package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfUserUI;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfUserUIDao {

    /**
     * 查询指定用户下的所有记录
     */
     List<ConfUserUI> getConfUserUIList(String userId);

    /**
     * 查询所有用户类型信息
     * @return List<ConfUserUI>对象SON格式输出
     */
    List<ConfUserUI> getConfUserUIInfo();

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


