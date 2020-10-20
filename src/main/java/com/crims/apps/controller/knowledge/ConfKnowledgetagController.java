package com.crims.apps.controller.knowledge;

import com.crims.apps.config.Result;
import com.crims.apps.model.knowledge.ConfKnowledgetag;
import com.crims.apps.model.knowledge.ConfKnowledgetag;
import com.crims.apps.service.knowledge.ConfKnowledgetagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin   //解决跨域
@RequestMapping("confknowledgetag")
@Slf4j  //打印日志
public class ConfKnowledgetagController {
    @Autowired
    private ConfKnowledgetagService confKnowledgetagService;

    //查询所有标签
    @GetMapping("findConfKnowledgetag")
    public Result<List<ConfKnowledgetag>> findConfKnowledgetag(){
      List<ConfKnowledgetag> list = confKnowledgetagService.findConfKnowledgetag();
        return Result.success(list);
    }

    //添加标签
    @PostMapping("saveConfKnowledgetag")
    public Result<String> saveConfKnowledgetag(@RequestBody ConfKnowledgetag confKnowledgetag){
        confKnowledgetagService.saveConfKnowledgetag(confKnowledgetag);
        return Result.success("添加成功");
    }
}
