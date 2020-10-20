package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfChannelnum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfChannelnumDao {

    ConfChannelnum getChannelNumByNodeID(String nodeid);

    List<ConfChannelnum> getChannelNumInfo();

    int insertChannelNumInfo(ConfChannelnum confChannelnum);

    int updateChannelNumInfo(ConfChannelnum confChannelnum);
}
