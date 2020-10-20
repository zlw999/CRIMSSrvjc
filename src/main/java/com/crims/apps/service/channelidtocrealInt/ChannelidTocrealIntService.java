package com.crims.apps.service.channelidtocrealInt;

import com.crims.apps.model.confinfo.ConfChannelidTocrealInt;

public interface ChannelidTocrealIntService {

    int insertConfChannelidTocrealInt(ConfChannelidTocrealInt confChannelidTocrealInt);

    ConfChannelidTocrealInt getConfChannelidTocrealIntInfo(String tb16ID);

    int updateChannelidTocrealInt(ConfChannelidTocrealInt confChannelidTocrealInt);

}
