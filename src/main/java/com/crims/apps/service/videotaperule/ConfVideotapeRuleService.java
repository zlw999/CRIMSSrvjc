package com.crims.apps.service.videotaperule;

import com.crims.apps.model.confinfo.ConfVideotapeRule;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ConfVideotapeRuleService {

    /**
     * 查询所有通道录像时间规则信息
     * @return List<ConfVideotapeRule>对象SON格式输出
     */
    List<ConfVideotapeRule> getConfVideotapeRuleList();

    /**
     * 查询所有通道录像时间规则信息(分页)
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfVideotapeRule>对象集分页形式以JSON格式输出
     */
    PageInfo<ConfVideotapeRule> getAll(int currentPage, int pageSize);

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
     * @param channelID
     * @return int
     */
    int deleteConfVideotapeRule(String channelID);
}
