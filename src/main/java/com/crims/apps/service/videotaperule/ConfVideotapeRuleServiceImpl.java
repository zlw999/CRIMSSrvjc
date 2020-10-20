package com.crims.apps.service.videotaperule;

import com.crims.apps.dao.confinfo.ConfVideotapeRuleDao;
import com.crims.apps.model.confinfo.ConfVideotapeRule;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfVideotapeRuleServiceImpl implements ConfVideotapeRuleService{

    @Autowired
    private ConfVideotapeRuleDao confVideotapeRuleDao;

    /**
     * 查询所有通道录像时间规则信息
     * @return List<ConfVideotapeRule>对象SON格式输出
     */
    public List<ConfVideotapeRule> getConfVideotapeRuleList(){
        return confVideotapeRuleDao.getConfVideotapeRuleList();
    }

    /**
     * 查询所有通道录像时间规则信息(分页)
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfVideotapeRule>对象集分页形式以JSON格式输出
     */
    @Override
    public PageInfo<ConfVideotapeRule> getAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<ConfVideotapeRule> pageList = confVideotapeRuleDao.getConfVideotapeRuleList();
        PageInfo<ConfVideotapeRule> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    /**
     * 根据通道ID查询通道录像时间规则信息
     * @param channelID
     * @return ConfVideotapeRule
     */
    public ConfVideotapeRule getConfVideotapeRuleInfo(String channelID){
        return confVideotapeRuleDao.getConfVideotapeRuleInfo(channelID);
    }

    /**
     * 添加通道录像时间规则信息
     * @param confVideotapeRule
     * @return int
     */
    public int insertConfVideotapeRuleInfo(ConfVideotapeRule confVideotapeRule){
        return confVideotapeRuleDao.insertConfVideotapeRuleInfo(confVideotapeRule);
    }

    /**
     * 修改通道录像时间规则信息
     * @param confVideotapeRule
     * @return int
     */
    public int updateConfVideotapeRule(ConfVideotapeRule confVideotapeRule){
        return confVideotapeRuleDao.updateConfVideotapeRule(confVideotapeRule);
    }

    /**
     * 删除通道录像时间规则信息
     * @param channelID
     * @return int
     */
    public int deleteConfVideotapeRule(String channelID){
        return confVideotapeRuleDao.deleteConfVideotapeRule(channelID);
    }
}
