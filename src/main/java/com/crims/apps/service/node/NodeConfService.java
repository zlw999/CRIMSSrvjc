package com.crims.apps.service.node;

import com.crims.apps.model.confinfo.ConfNode;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NodeConfService {

    /**
     * 查询所有节点信息
     * @return List<ConfNode>对象集以JSON格式输出
     */
    List<ConfNode> getALL();

    /**
     * 根据节点名称模糊查询节点配置信息(分页查询)
     * @param nodeName
     * @return List<ConfNode>对象集分页形式以JSON格式输出
     */
    PageInfo<ConfNode> getConfNodeList(String nodeName, int currentPage, int pageSize);

    /**
     * 根据节点id查询节点信息
     * @param nodeid
     * @return ConfNode
     */
    ConfNode getNodeInfoByNodeId(String nodeid);

    /**
     * 添加节点配置信息
     * @param confNode
     * @return int
     */
    int insertConfNode(ConfNode confNode);

    /**
     * 修改节点配置信息
     * @param confNode
     * @return int
     */
    int updateConfNode(ConfNode confNode);

    /**
     * 删除节点配置信息
     * @param confNode
     * @return int
     */
    int deleteConfNode(ConfNode confNode);
}
