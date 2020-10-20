package com.crims.apps.service.nettop;

import com.crims.apps.model.nettop.ConfEnum;

import java.util.List;

public interface ConfEnumService {

    List<ConfEnum> findEnumType();

    List<String> findEnumChildType();

    String findItemValue(String s1);

    String findItemChildValue(ConfEnum confEnum);

    String findItemName(String onlinestatu1);
}
