package com.crims.apps.controller.nettop;

import com.crims.apps.model.nettop.ConfNettop;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.crims.apps.config.Result;
import com.crims.apps.model.nettop.ConfNettop;
import com.crims.apps.service.nettop.ConfNettopService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin   //解决跨域
@RequestMapping("confnettop")
@Slf4j  //打印日志
public class ConfNettopController {
    @Autowired
    private ConfNettopService confNettopService;
    private final static Logger logger = LoggerFactory.getLogger(ConfNettopController.class);

    //查询所有  ----没有分页 返回集合
    @GetMapping("findAllPage")   //传参：不需要传参
    public Result<List<ConfNettop>> findAllPage(ConfNettop confNettop) {
          //调查询所有的方法
        List<ConfNettop> list = confNettopService.findAll(confNettop);
        return Result.success(list);
    }

    //查询所有
    /*
     * 查询所有 获取所有 并且遍历 遍历之后查询每一个的子集 进行set
     * 因为每一个有可能有自己的子集
     * 所以这里除了分页 加了一个通过主键获取所有的子集
     * 将查询到的集合遍历 一个一个将child set进去  这个返回如果成功会返回0状态码
     * **/
    @GetMapping("findAll")  //传参：当前页pageNum 最大页pageSize
    public Result<PageInfo<ConfNettop>> findAll(ConfNettop confNettop, @RequestParam(required = false, defaultValue = "1") int pageNum,
                                                @RequestParam(required = false, defaultValue = "5") int pageSize) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        //获取集合
        List<ConfNettop> list = confNettopService.findAll(confNettop);
        //遍历集合
        for (ConfNettop nettop : list) {
            //通过主键id 获取到他的子集
            int topid = nettop.getTopid();
            List<ConfNettop> confNettops = confNettopService.selectByParentId(topid);
            //进行set  这个子集 我在实体类里加了个属性 但是表里没有此字段
            nettop.setChildren(confNettops);
        }
        //获取到的集合 我们要存储大数据xml topdata  为了给前台传值 我又加了个xml字段
        //字段类型String  判断topdata是否为空 如果不为空 就将byte数组 转换为String类型 并且set
        for (ConfNettop nettop : list) {
            byte[] topdata = nettop.getTopdata();
           if(topdata!=null){
               String s = new String(topdata);
               nettop.setXml(s);
           }else{
               nettop.setXml(null);
           }
        }
        PageInfo<ConfNettop> page = new PageInfo<>(list);
        return Result.success(page);
    }

    //添加同级节点  insert  ---添加大数据xml文件   增同级节点     传参
    /*
     * 首先查出添加节点那里之后 其他的相同等级的集合 给每一个的sortid加1 空出来一个sortid进行添加
     * 并且查询时候通过sortid进行排序 返回给前端 就会达到添加在当前下面的实现**/
    @PostMapping("insert")
    public Result<String> insert(@RequestBody ConfNettop confNettop) {
        if(confNettop.getTopid()>0){
            String xml = confNettop.getXml();
            byte[] bytes = xml.getBytes();
            confNettop.setTopdata(bytes);
            confNettopService.update(confNettop);
            return Result.success("成功");
        }else{
        List<ConfNettop> list = confNettopService.findConfnettopByParentId(confNettop);
        for (ConfNettop nettop : list) {
            nettop.setSortid(nettop.getSortid() + 1);
            confNettopService.update(nettop);
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdf.format(date);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = df.parse(s);
            confNettop.setLastModifyTime(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
       /* byte[] bytes = FileUtils.getBytesByFile("D:/BaiduNetdiskDownload/xmls/100个svg.xml");
        confNettop.setTopdata(bytes);*/
        String xml = confNettop.getXml();
        byte[] bytes = xml.getBytes();
        // byte[] bytes = File2byte(data);
        confNettop.setTopdata(bytes);
        confNettopService.insert(confNettop);
        return Result.success("添加成功");}
    }

    public static byte[] File2byte(File tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }
    //添加 增子节点
    /*
     * 传参 topname 点击的topid 也就是我要添加的parentid  还有点击的下面的子集的length加1 作为添加的sortid**/
    @PostMapping("insertChild")
    public Result<String> insertChild(@RequestBody ConfNettop confNettop) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdf.format(date);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = df.parse(s);
            confNettop.setLastModifyTime(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String xml = confNettop.getXml();
        byte[] bytes = xml.getBytes();
        // byte[] bytes = File2byte(data);
        confNettop.setTopdata(bytes);
        confNettopService.insert(confNettop);
        return Result.success("添加成功");
    }


    @GetMapping("test")
    @ResponseBody
    public List<ConfNettop> test(ConfNettop confNettop) {

        return confNettopService.findConfnettopByParentId(confNettop);
    }

    //删除
    /*
     * 通过主键删除图文**/
    @PostMapping("delete")
    public Result<String> delete(@RequestBody ConfNettop confNettop) {
        int topid = confNettop.getTopid();
        confNettopService.delete(topid);
        return Result.success("删除成功");
    }

    //删除
    /*
     * 删除有子集的对象 前台传过来数组 遍历删除**/
    @PostMapping("deleteAll")
    public Result<String> deleteAll(@RequestBody int[] arr) {
        for (int topid : arr) {
            confNettopService.delete(topid);
        }
        return Result.success("删除成功");
    }

    //传六个参数  topid topname parentid sortid  要互换的topid-》id 还有子集children 向上下走
    /*
     * **/
    @PostMapping("updateByUpTest1")
    public Result<String> updateByUpTest1(@RequestBody ConfNettop confNettop) {
        int topid = confNettop.getTopid();  //3
        String topname = confNettop.getTopname();  //孙
        int parentid = confNettop.getParentid();  //1
        int sortid = confNettop.getSortid();  //2
        int id = confNettop.getId();
        List<ConfNettop> children = confNettop.getChildren();

        //上面是传过来的对象属性 需要变换  topid减1 topname不变 parentid不变 sortid减1
        //通过topid减1 获取对象属性
        ConfNettop confNettopOld = confNettopService.findConfnettopById(id);
        // confNettopOld  2 钱 1 1
        int topidOld = confNettopOld.getTopid();  //2
        String topnameOld = confNettopOld.getTopname();  //钱
        int parentidOld = confNettopOld.getParentid();  //1
        int sortidOld = confNettopOld.getSortid();  //1
        List<ConfNettop> childrenOld = confNettopService.selectByParentId(topidOld);
        if (children != null) {
            for (ConfNettop child : children) {

                child.setParentid(topidOld);
                confNettopService.update(child);

            }
        }
        if (childrenOld != null) {
            for (ConfNettop child1 : childrenOld) {
                child1.setParentid(topid);
                confNettopService.update(child1);
            }
        }
        //我需要把 confNettop变成 2 孙 1 1
        confNettop.setTopid(topidOld);
        confNettop.setTopname(topname);
        confNettop.setParentid(parentid);
        confNettop.setSortid(sortidOld);
        confNettop.setChildren(children);
        String xml = confNettop.getXml();
        byte[] bytes = xml.getBytes();
        confNettop.setTopdata(bytes);
        confNettop.setOperateuserid(confNettop.getOperateuserid());
        confNettop.setOperateusername(confNettop.getOperateusername());
        confNettop.setLastModifyTime(confNettop.getLastModifyTime());

        //我需要把 confNettopOld变成 3 钱 1 2
        confNettopOld.setTopid(topid);
        confNettopOld.setTopname(topnameOld);
        confNettopOld.setParentid(parentidOld);
        confNettopOld.setSortid(sortid);
        confNettopOld.setChildren(childrenOld);
        confNettopOld.setTopdata(confNettopOld.getTopdata());
        confNettopOld.setOperateuserid(confNettopOld.getOperateuserid());
        confNettopOld.setOperateusername(confNettopOld.getOperateusername());
        confNettopOld.setLastModifyTime(confNettopOld.getLastModifyTime());
        //confNettopService.delete(topid);
        //confNettopService.delete(topidOld);
        confNettopService.update(confNettop);
        confNettopService.update(confNettopOld);
        return Result.success("成功");
    }


    //传五个参数  topid topname 父节点的parentid 父节点的sortid+1 children   左移

    @PostMapping("updateByUpTest2")
    public Result<String> updateByUpTest2(@RequestBody ConfNettop confNettop) {

        List<ConfNettop> list = confNettopService.findConfnettopByParentId(confNettop);
        for (ConfNettop nettop : list) {
            nettop.setSortid(nettop.getSortid() + 1);
            confNettopService.update(nettop);
        }
        confNettopService.updateConfNettopWithId(confNettop);
        return Result.success("成功");
    }

    //传五个参数  topid topname 兄弟节点的topid 名是parentid 兄弟节点的children长度 children   右移

    @PostMapping("updateByUpTest3")
    public Result<String> updateByUpTest3(@RequestBody ConfNettop confNettop) {

        List<ConfNettop> list = confNettopService.findConfnettopByParentId(confNettop);
        for (ConfNettop nettop : list) {
            nettop.setSortid(nettop.getSortid() - 1);
            confNettopService.update(nettop);
        }
        confNettopService.updateConfNettopWithId(confNettop);
        return Result.success("成功");
    }


    //保存xml数据  传过来id 我对其进行修改
   @PostMapping("saveXml")
    public Result<String> saveXml(@RequestBody ConfNettop confNettop){
        // byte[] bytes = FileUtils.getBytesByFile("D:/111.txt");
       // confNettop.setTopdata(bytes);
       String xml = confNettop.getXml();
       byte[] bytes = xml.getBytes();
       confNettop.setTopdata(bytes);
        confNettopService.update1(confNettop);
        return Result.success("成功");
    }

    //通过主键id 查询xml数据
    @GetMapping("findConfNettopById")
    public Result<ConfNettop> findConfNettopById(ConfNettop confNettop){
        ConfNettop nettop = confNettopService.findConfNettopById(confNettop);
try {

    byte[] topdata = nettop.getTopdata();

    if(topdata!=null){
        String s = new String(topdata);
        nettop.setXml(s);
    }else{
        nettop.setXml(null);
    }

}catch (Exception e){
    System.out.println("问题不大");
}
        return Result.success(nettop);
    }

}