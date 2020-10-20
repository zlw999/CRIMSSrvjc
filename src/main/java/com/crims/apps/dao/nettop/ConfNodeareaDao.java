package com.crims.apps.dao.nettop;

import com.crims.apps.model.nettop.ConfNodearea;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConfNodeareaDao {
    List<ConfNodearea> findAll();

    String findNodeNameById(String nodeidnew);
}
