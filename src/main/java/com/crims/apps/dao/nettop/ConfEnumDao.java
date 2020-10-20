package com.crims.apps.dao.nettop;

import com.crims.apps.model.nettop.ConfEnum;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConfEnumDao {
    List<ConfEnum> findEnumType();

    List<String> findEnumChildType();

    String findItemValue(String s1);

    String findItemChildValue(ConfEnum confEnum);

    String findItemName(String onlinestatu1);
}
