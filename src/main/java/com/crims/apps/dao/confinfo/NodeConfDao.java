package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NodeConfDao {

    /**
     * 查询所有节点信息
     * @return List<ConfNode>对象集以JSON格式输出
     */
    List<ConfNode> getALL();

    /**
     * 根据节点名称模糊查询节点配置信息(分页查询)
     * @param nodeNameStr
     * @return List<ConfNode>对象集分页形式以JSON格式输出
     */
    List<ConfNode> getConfNodeList(String nodeNameStr);

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
