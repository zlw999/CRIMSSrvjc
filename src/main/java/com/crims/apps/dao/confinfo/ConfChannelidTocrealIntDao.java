package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfChannelidTocrealInt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfChannelidTocrealIntDao {

    int insertConfChannelidTocrealInt(ConfChannelidTocrealInt confChannelidTocrealInt);

    ConfChannelidTocrealInt getConfChannelidTocrealIntInfo(String tb16ID);

    int updateChannelidTocrealInt(ConfChannelidTocrealInt confChannelidTocrealInt);

}
