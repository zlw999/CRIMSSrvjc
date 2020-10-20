package com.crims.apps.controller.ddman;

import com.crims.apps.model.confinfo.ConfEnum;
import com.crims.apps.service.ddman.DDManService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ddman/confenum")
public class DDManController {

    public static Logger logger = LoggerFactory.getLogger(DDManController.class);

    @Autowired
    private DDManService ddmanService;

    /**
     * 查询所有字典信息(分页查询)
     *
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfEnum>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getAll")
    @ResponseBody
    @ApiOperation(value = "查询所有字典信息(分页查询)", notes = "confenum_info")
    public PageInfo<ConfEnum> getAll(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<ConfEnum> confEnumeList = ddmanService.getAll(currentPage, pageSize);
        return confEnumeList;
    }

    /**
     * 查询所有字典信息(不分页查询)
     * @return List<ConfEnum>对象集以JSON格式输出
     */
    @GetMapping("/getConfEnumList")
    @ResponseBody
    @ApiOperation(value = "查询所有字典信息(不分页查询)", notes = "confenum_info")
    public List<ConfEnum> getConfEnumList(){
        return ddmanService.getConfEnumList();
    }

    /**
     * 查询某项下所有子项信息
     * @param id
     * @return ConfEnum对象转JSON格式输出
     */
    @GetMapping("/getId")
    @ResponseBody
    @ApiOperation(value = "查询某项下所有子项信息", notes = "confenum_info")
    public List<ConfEnum> getId(String id) {
        return ddmanService.getId(id);
    }

    /**
     * 查询某子项信息
     * @param id
     * @param item_value
     * @return ConfEnum对象转JSON格式输出
     */
    @GetMapping("/getIdANDItem_value")
    @ResponseBody
    @ApiOperation(value = "查询某子项信息", notes = "confenum_info")
    public ConfEnum getIdANDItem_value(String id,String item_value) {
        return ddmanService.getItem_value(item_value,id);
    }
}
