package com.crims.apps.controller.device;

import com.crims.apps.common.Result;
import com.crims.apps.common.struct.CommonColumn;
import com.crims.apps.model.confinfo.ConfEnum;
import com.crims.apps.service.ddman.DDManService;
import com.crims.apps.service.device.camerainfo.ConfCamerainfoService;
import com.crims.common.constants.BaseConst;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/camerainfo")
public class ConfCamerainfoController {


    @Autowired
    private DDManService ddmanService;

    @Autowired
    ConfCamerainfoService confCamerainfoService;

    @RequestMapping(value = "/insertCameraInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("摄像机扩展属性表字段")
    public Result<Object> insertCameraInfo(){

        Result<Object> result = new Result<Object>();

        List<CommonColumn> commonColumnList = new ArrayList<>();

        List<ConfEnum> confEnumList = ddmanService.getId(BaseConst.DBCAMERA);

        List<String> colList = confCamerainfoService.getColumnName();

        if (confEnumList != null && !confEnumList.isEmpty()) {
            for (int i = 0; i < confEnumList.size(); i++) {
                String dsp = confEnumList.get(i).getDsp();
                String[] str = dsp.split(";");
                String dsp0 = str[0];
                String colStr = getColStr(dsp0);

                String dsp1 = str[1];
                String type = getColStr(dsp1);
                String typeName = "";
                if("1".equals(type)){
                    typeName = "byte";
                }
                if("2".equals(type)){
                    typeName = "int";
                }
                if("3".equals(type)){
                    typeName = "char";
                }
                if("4".equals(type)){
                    typeName = "long";
                }
                if("5".equals(type)){
                    typeName = "varchar";
                }
                if("6".equals(type)){
                    typeName = "float";
                }
                if("7".equals(type)){
                    typeName = "double";
                }
                if("8".equals(type)){
                    typeName = "int";
                }
                if("9".equals(type)){
                    typeName = "varchar";
                }
                if("10".equals(type)){
                    typeName = "varchar";
                }
                if("11".equals(type)){
                    typeName = "varchar";
                }
                if("12".equals(type)){
                    typeName = "datetime";
                }
                String dsp2 = str[2];
                String length = getColStr(dsp2);
                if (!colList.contains(colStr)) {
                    CommonColumn commonColumn = new CommonColumn();
                    commonColumn.setName(colStr);
                    commonColumn.setType(typeName);
                    commonColumn.setLength(Integer.parseInt(length));
                    commonColumnList.add(commonColumn);
                }
            }
        }

        if(commonColumnList != null && !commonColumnList.isEmpty()){
            confCamerainfoService.insertConfCameraInfo(commonColumnList);
            result.setCode("0");
            result.setMsg("摄像机扩展属性表字段完成");
        }else{
            result.setCode("1");
            result.setMsg("无摄像机扩展属性字段");
        }

        return result;
    }

    public String getColStr(String dsp){
        int index = dsp.indexOf("=");
        String colStr = dsp.substring(index + 1);
        return colStr;
    }

}
