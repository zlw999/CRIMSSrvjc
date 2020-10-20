package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfVideotapeRule;
import com.crims.apps.model.confinfo.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfVideotapeRuleDao {

    /**
     * 查询所有通道录像时间规则信息
     * @return List<ConfVideotapeRule>对象SON格式输出
     */
    List<ConfVideotapeRule> getConfVideotapeRuleList();

    /**
     * 根据通道ID查询通道录像时间规则信息
     * @param channelID
     */
    ConfVideotapeRule getConfVideotapeRuleInfo(String channelID);

    /**
     * 添加通道录像时间规则信息
     * @param confVideotapeRule
     * @return int
     */
    int insertConfVideotapeRuleInfo(ConfVideotapeRule confVideotapeRule);

    /**
     * 修改通道录像时间规则信息
     * @param confVideotapeRule
     * @return int
     */
    int updateConfVideotapeRule(ConfVideotapeRule confVideotapeRule);

    /**
     * 删除通道录像时间规则信息
     * @param deviceInfo
     * @return int
     */
    int deleteDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 删除通道录像时间规则信息
     * @param channelID
     * @return int
     */
    int deleteConfVideotapeRule(String channelID);
}
