package com.crims.apps.service.channelnum;

import com.crims.apps.model.confinfo.ConfChannelnum;

import java.util.List;

public interface ConfChannelnumService {

    ConfChannelnum getChannelNumByNodeID(String nodeid);

    List<ConfChannelnum> getChannelNumInfo();

    int insertChannelNumInfo(ConfChannelnum confChannelnum);

    int updateChannelNumInfo(ConfChannelnum confChannelnum);
}
