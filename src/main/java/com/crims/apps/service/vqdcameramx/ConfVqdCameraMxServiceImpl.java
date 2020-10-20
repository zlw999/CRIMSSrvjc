package com.crims.apps.service.vqdcameramx;

import com.crims.apps.dao.confinfo.ConfVqdCameraMxDao;
import com.crims.apps.model.confinfo.ConfVqdCameraMx;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfVqdCameraMxServiceImpl implements ConfVqdCameraMxService {

    @Autowired
    private ConfVqdCameraMxDao confVqdCameraMxDao;

    /**
     * 查询设备门限配置信息(不分页)
     * @return List<ConfVqdCameraMx>
     */
    @Override
    public List<ConfVqdCameraMx> getALL() {
        return confVqdCameraMxDao.getALL();
    }

    /**
     * 查询设备门限配置信息(分页)
     * @param currentPage
     * @param pageSize
     * @return PageInfo<ConfVqdCameraMx>
     */
    @Override
    public PageInfo<ConfVqdCameraMx> getConfVqdCameraMxList(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<ConfVqdCameraMx> pageList = confVqdCameraMxDao.getALL();
        PageInfo<ConfVqdCameraMx> pageInfo = new PageInfo<ConfVqdCameraMx>(pageList);
        return pageInfo;
    }

    /**
     * 添加设备门限配置信息
     * @param record
     * @return int
     */
    @Override
    public int insertConfVqdCameraMxInfo(ConfVqdCameraMx record) {
        return confVqdCameraMxDao.insertConfVqdCameraMxInfo(record);
    }

    /**
     * 修改设备门限配置信息
     * @param record
     * @return int
     */
    @Override
    public int updateConfVqdCameraMxInfo(ConfVqdCameraMx record) {
        return confVqdCameraMxDao.updateConfVqdCameraMxInfo(record);
    }

    /**
     * 删除设备门限配置信息
     * @param record
     * @return int
     */
    @Override
    public int deleteConfVqdCameraMxInfo(ConfVqdCameraMx record) {
        return confVqdCameraMxDao.deleteConfVqdCameraMxInfo(record);
    }
}
