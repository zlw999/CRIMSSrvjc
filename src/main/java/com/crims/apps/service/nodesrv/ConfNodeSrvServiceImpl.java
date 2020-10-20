package com.crims.apps.service.nodesrv;

import com.crims.apps.dao.confinfo.ConfNodeSrvDao;
import com.crims.apps.model.confinfo.ConfNodeSrv;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfNodeSrvServiceImpl implements ConfNodeSrvService{

    @Autowired
    private ConfNodeSrvDao confNodeSrvDao;

    /**
     * 查询节点服务信息
     * @return List<ConfNodeSrv>对象集以JSON格式输出
     */
    public List<ConfNodeSrv> getALL(){
        return confNodeSrvDao.getALL();
    }

    /**
     * 查询节点服务信息(分页查询)
     * @param currentPage
     * @param pageSize
     * @return List<ConfNodeSrv>对象集分页形式以JSON格式输出
     */
    @Override
    public PageInfo<ConfNodeSrv> getConfNodeSrvList(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<ConfNodeSrv> pageList = confNodeSrvDao.getALL();
        PageInfo<ConfNodeSrv> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    /**
     * 添加节点服务信息
     * @param record
     * @return int
     */
    public int insertConfNodeSrvInfo(ConfNodeSrv record){
        return confNodeSrvDao.insertConfNodeSrvInfo(record);
    }

    /**
     * 修改节点服务信息
     * @param record
     * @return int
     */
    public int updateConfNodeSrvInfo(ConfNodeSrv record){
        return confNodeSrvDao.updateConfNodeSrvInfo(record);
    }

    /**
     * 删除节点服务信息
     * @param record
     * @return int
     */
    public int deleteConfNodeSrvInfo(ConfNodeSrv record){
        return confNodeSrvDao.deleteConfNodeSrvInfo(record);
    }
}
