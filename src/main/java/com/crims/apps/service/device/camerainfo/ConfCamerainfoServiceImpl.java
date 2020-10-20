package com.crims.apps.service.device.camerainfo;

import com.crims.apps.common.struct.CommonColumn;
import com.crims.apps.dao.confinfo.ConfCamerainfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfCamerainfoServiceImpl implements ConfCamerainfoService{

    @Autowired
    private ConfCamerainfoDao confCamerainfoDao;

    @Override
    public void insertConfCameraInfo(List<CommonColumn> list) {
        if(list != null && !list.isEmpty()){
            for (int i = 0; i < list.size(); i++) {
                confCamerainfoDao.insertConfCameraInfo(list.get(i));
            }
        }
    }

    @Override
    public List<String> getColumnName() {
        return confCamerainfoDao.getColumnName();
    }
}
