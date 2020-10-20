package com.crims.apps.dao.sysconfig;

import com.crims.apps.common.struct.ConfSysini;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfSysiniDao {

    List<ConfSysini> getConfSysini();

    String findimgpath();

    String findimgpath1();

    String findfilepath();

    String findfilepath1();
}
