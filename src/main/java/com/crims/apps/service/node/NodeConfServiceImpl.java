package com.crims.apps.service.node;

import com.crims.apps.dao.confinfo.NodeConfDao;
import com.crims.apps.model.confinfo.ConfNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeConfServiceImpl implements NodeConfService {

    @Autowired
    private NodeConfDao nodeConfDao;

    /**
     * 查询所有节点信息
     * @return List<ConfNode>对象集以JSON格式输出
     */
    public List<ConfNode> getALL(){
        List<ConfNode> confNodelist = nodeConfDao.getALL();
        if(confNodelist != null && !confNodelist.isEmpty()){
            for(int i = 0;i<confNodelist.size();i++){
                confNodelist.get(i).setNodeidarea(confNodelist.get(i).getNodeid());
                confNodelist.get(i).setNodeidone(confNodelist.get(i).getNodeid());
            }
        }
        return confNodelist;
    }

    /**
     * 根据节点名称模糊查询节点配置信息(分页查询)
     * @param nodeName
     * @return List<ConfNode>对象集分页形式以JSON格式输出
     */
    @Override
    public PageInfo<ConfNode> getConfNodeList(String nodeName, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        StringBuilder nodeNameStr = new StringBuilder("%");
        if(nodeName == null){
            nodeName = "";
        }
        nodeNameStr.append(nodeName);
        nodeNameStr.append("%");
        List<ConfNode> pageList = nodeConfDao.getConfNodeList(nodeNameStr.toString());
        if(pageList != null && !pageList.isEmpty()){
            for(int i = 0;i<pageList.size();i++){
                pageList.get(i).setNodeidone(pageList.get(i).getNodeid());
                pageList.get(i).setNodeidarea(pageList.get(i).getNodeid());
            }
        }
        PageInfo<ConfNode> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    /**
     * 根据节点id查询节点信息
     * @param nodeid
     * @return ConfNode
     */
    @Override
    public ConfNode getNodeInfoByNodeId(String nodeid) {
        return nodeConfDao.getNodeInfoByNodeId(nodeid);
    }

    /**
     * 添加节点配置信息
     * @param confNode
     * @return int
     */
    public int insertConfNode(ConfNode confNode){
        return nodeConfDao.insertConfNode(confNode);
    }

    /**
     * 修改节点配置信息
     * @param confNode
     * @return int
     */
    public int updateConfNode(ConfNode confNode){
        return nodeConfDao.updateConfNode(confNode);
    }

    /**
     * 删除节点配置信息
     * @param confNode
     * @return int
     */
    public int deleteConfNode(ConfNode confNode){
        return nodeConfDao.deleteConfNode(confNode);
    }
}
