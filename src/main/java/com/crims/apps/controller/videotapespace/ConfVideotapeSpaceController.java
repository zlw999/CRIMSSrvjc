package com.crims.apps.controller.videotapespace;

import com.crims.apps.common.Result;
import com.crims.apps.model.confinfo.ConfVideotapeDrive;
import com.crims.apps.model.confinfo.ConfVideotapeSpace;
import com.crims.apps.model.operlog.OperateLogInfo;
import com.crims.apps.service.device.channel.ChannelInfoService;
import com.crims.apps.service.operlog.OperateLogInfoService;
import com.crims.apps.service.videotapedrive.ConfVideotapeDriveService;
import com.crims.apps.service.videotapespace.ConfVideotapeSpaceService;
import com.crims.common.constants.BaseConst;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.*;

@Controller
@RequestMapping("/videotapespace")
public class ConfVideotapeSpaceController {

    public static Logger logger = LoggerFactory.getLogger(ConfVideotapeSpaceController.class);

    @Autowired
    private ConfVideotapeSpaceService confVideotapeSpaceService;

    @Autowired
    private ConfVideotapeDriveService confVideotapeDriveService;

    @Autowired
    private ChannelInfoService channelInfoService;

    @Autowired
    private OperateLogInfoService operateLogInfoService;

    String[] channelID = new String[0]; //通道数组

    /**
     * 获取本地盘符并添加到数据库
     * @return Result结果集
     */
    @RequestMapping(value = "/getConfVideotapedriveInfo",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取服务器盘符并添加到数据库")
    public Result<Object> getConfVideotapedriveInfo(String loginUserID,String loginUserName){
        Result<Object> result = new Result<>();
        try {
            String os = System.getProperty("os.name");
            List<ConfVideotapeDrive> list = new ArrayList<>();
            List<ConfVideotapeDrive> driveNameList = confVideotapeDriveService.getVideotapeDriveList();
            if(os.toLowerCase().startsWith("win")){
                File[] roots = File.listRoots();
                for (int i = 0; i < roots.length; i++) {
                    ConfVideotapeDrive confVideotapeDrive = new ConfVideotapeDrive();
                    String driveName = roots[i].toString();
                    File file = new File(driveName);
                    long totalSpace = file.getTotalSpace();
                    long freeSpace = file.getFreeSpace();
                    long usedSpace = totalSpace - freeSpace;
                    confVideotapeDrive.setDrivename(driveName);
                    confVideotapeDrive.setTotalCapacity(totalSpace / 1024 / 1024 / 1024 + "G");
                    confVideotapeDrive.setUsedCapacity(usedSpace / 1024 / 1024 / 1024 + "G");
                    confVideotapeDrive.setFreeCapacity(freeSpace / 1024 / 1024 / 1024 + "G");
                    list.add(confVideotapeDrive);
                    if(!driveNameList.stream().filter(w->w.getDrivename().equals(driveName)).findAny().isPresent()){
                        confVideotapeDrive.setOperateaction(BaseConst.INSERTACTION); //操作标志 1 添加
                        confVideotapeDrive.setLastmodifytime(new Date());
                        confVideotapeDrive.setOperateuserid(loginUserID);
                        confVideotapeDrive.setOperateusername(loginUserName);
                        confVideotapeDriveService.insertVideotapeDriveInfo(confVideotapeDrive);
                    }
                }
            }else{
                File file = new File("/usr");
                long totalSpace = file.getTotalSpace();
                long freeSpace = file.getFreeSpace();
                long usedSpace = totalSpace - freeSpace;
                ConfVideotapeDrive confVideotapeDrive = new ConfVideotapeDrive();
                confVideotapeDrive.setDrivename("/usr");
                confVideotapeDrive.setTotalCapacity(totalSpace / 1024 / 1024 / 1024 + "G");
                confVideotapeDrive.setFreeCapacity(freeSpace / 1024 / 1024 / 1024 + "G");
                confVideotapeDrive.setUsedCapacity(usedSpace / 1024 / 1024 / 1024 + "G");
                list.add(confVideotapeDrive);
                if(!driveNameList.stream().filter(w->w.getDrivename().equals(confVideotapeDrive.getDrivename())).findAny().isPresent()){
                    confVideotapeDrive.setOperateaction(BaseConst.INSERTACTION); //操作标志 1 添加
                    confVideotapeDrive.setLastmodifytime(new Date());
                    confVideotapeDrive.setOperateuserid(loginUserID);
                    confVideotapeDrive.setOperateusername(loginUserName);
                    confVideotapeDriveService.insertVideotapeDriveInfo(confVideotapeDrive);
                }
            }
            result.setResult(list);
        } catch (Exception e) {
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }
        return result;
    }

    /**
     * 查询所有通道视频存储信息
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfVideotapeSpace>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getConfVideotapeSpaceList")
    @ResponseBody
    @ApiOperation(value = "查询所有通道视频存储信息", notes = "ConfVideotapeSpace_info")
    public PageInfo<ConfVideotapeSpace> getConfVideotapeSpaceList(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<ConfVideotapeSpace> confVideotapeSpaceList = confVideotapeSpaceService.getConfVideotapeSpaceList(currentPage, pageSize);
        return confVideotapeSpaceList;
    }

    /**
     * 根据通道ID查询通道视频存储信息
     * @param loginUserID
     * @param loginUserName
     * @param channelID
     * @return result
     */
    @PostMapping("/getConfVideotapeSpaceByID")
    @ResponseBody
    @ApiOperation("根据通道ID查询通道视频存储信息")
    public Result getConfVideotapeSpaceByID(String loginUserID,String loginUserName,String channelID){
        Result<Object> result = new Result<Object>();
        try {
            List<ConfVideotapeSpace> confVideotapeSpaceList = confVideotapeSpaceService.getConfVideotapeSpaceByID(channelID);
            result.setResult(confVideotapeSpaceList);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_VideotapeSpace);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_VideotapeSpace_Query);
            operateLogInfo.setOperatedsp("根据通道ID查询通道视频存储信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        } catch (Exception e) {
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }
        return result;
    }

    /**
     * 添加通道视频存储信息
     * @param loginUserID
     * @param loginUserName
     * @param confVideotapeSpaceList
     * @return Result 结果集
     */
    @RequestMapping(value = "/insertConfVideotapeSpaceInfo",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加通道视频存储信息")
    public Result<Object> insertConfVideotapeSpaceInfo(String loginUserID,String loginUserName,@RequestBody List<ConfVideotapeSpace> confVideotapeSpaceList){
        Result<Object> result = new Result<>();
        try {
            if(confVideotapeSpaceList != null && !confVideotapeSpaceList.isEmpty()){

                int j = 0; //通道目录索引

                int k = 1; //通道录像文件索引

                for(int i = 0;i<confVideotapeSpaceList.size();i++){

                    if("1".equals(confVideotapeSpaceList.get(i). getFileiscreated())){

                        //添加通道ID
                        if (channelID.length == 0) {  //若数组为空，定义数组的长度为1
                            channelID = new String[1];
                            channelID[0] = confVideotapeSpaceList.get(i).getChannel_id();
                        }

                        if(!Arrays.asList(channelID).contains(confVideotapeSpaceList.get(i).getChannel_id())){
                            //若数组不为空，把数组复制出一个新的，在原数组的基础上加1
                            String[] copy = Arrays.copyOf(channelID, channelID.length + 1);
                            //把原先数组制空
                            channelID = null;
                            //把新数组给原先这个数组
                            channelID = copy;
                            channelID[channelID.length - 1] = confVideotapeSpaceList.get(i).getChannel_id();
                            j = 0;
                        }

                        //检测通道目录，判断通道目录中的文件个数，目录超过10000个文件，继续创建该通道新的目录
                        if(Arrays.asList(channelID).contains(confVideotapeSpaceList.get(i).getChannel_id())){
                            int m = 0;
                            while(true){
                                File file1 = new File(confVideotapeSpaceList.get(i).getFile_path() + "/" + confVideotapeSpaceList.get(i).getChannel_id() + "_" + m);
                                if(file1.exists()){
                                    int fileCount = getTxtFilesCount(confVideotapeSpaceList.get(i).getFile_path() + "/" + confVideotapeSpaceList.get(i).getChannel_id() + "_" + m);
                                    if(fileCount > 10000){
                                        m++;
                                    }else{
                                        break;
                                    }
                                }else{
                                    break;
                                }
                            }
                            j = m;
                        }

                        //磁盘路径
                        String filePathRoot = confVideotapeSpaceList.get(i).getFile_path();

                        //通道录像目录
                        String filePathChannel = filePathRoot + "/" + confVideotapeSpaceList.get(i).getChannel_id() + "_" + j;

                        //创建通道目录对象
                        File channelFile = new File(filePathChannel);

                        if(!channelFile.exists()){
                            //创建通道目录
                            channelFile.mkdir();
                        }

                        //通道录像文件路径
                        String file_path = filePathChannel + "/" + confVideotapeSpaceList.get(i).getChannel_id() + "_" + k + ".mp4";

                        File file = new File(file_path);

                        //检测通道目录下的文件是否重复
                        File fileNew = checkFileRepeat(k,file,filePathChannel,file_path,confVideotapeSpaceList.get(i));

                        //创建通道录像文件
                        fileNew.createNewFile();

                        RandomAccessFile raf = new RandomAccessFile(fileNew, "rw");

                        //设置通道录像文件大小
                        raf.setLength(confVideotapeSpaceList.get(i).getFilesize() * 1024 * 1024);

                        raf.close();

                        confVideotapeSpaceList.get(i).setOperateaction(BaseConst.INSERTACTION); //操作标志 1 添加
                        confVideotapeSpaceList.get(i).setLastmodifytime(new Date());
                        confVideotapeSpaceList.get(i).setOperateuserid(loginUserID);
                        confVideotapeSpaceList.get(i).setOperateusername(loginUserName);

                        //数据库插入记录
                        confVideotapeSpaceService.insertConfVideotapeSpaceInfo(confVideotapeSpaceList.get(i));

                        result.setCode("0");
                        result.setMsg("文件创建成功");
                    }
                }
            }

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_VideotapeSpace);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_VideotapeSpace_Add);
            operateLogInfo.setOperatedsp("添加通道视频存储信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        } catch (Exception e) {
            logger.error("添加通道视频存储信息异常：" + e.toString());
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 删除通道视频存储信息
     * @param loginUserID
     * @param loginUserName
     * @param file_path
     * @return Result结果集
     */
    @PostMapping("/deleteConfVideotapeSpaceInfo")
    @ResponseBody
    @ApiOperation("删除通道视频存储信息")
    public Result<Object> deleteConfVideotapeSpaceInfo(String loginUserID,String loginUserName,String file_path){
        Result<Object> result = new Result<>();
        try {
            int num = confVideotapeSpaceService.deleteConfVideotapeSpaceInfo(file_path);
            if(num > 0){
                File file = new File(file_path);
                if(file.exists() && file.isFile()){
                    //删除文件
                    file.delete();
                }
            }

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_VideotapeSpace);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_VideotapeSpace_Delete);
            operateLogInfo.setOperatedsp("删除通道视频存储信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 压缩通道视频存储信息
     * @param channelID 通道ID
     * @param decrementStr 压缩量
     * @return Result结果集
     */
    @PostMapping("/deleteVideotapeSpaceInfoByNum")
    @ResponseBody
    @ApiOperation("压缩通道视频存储信息")
    public Result<Object> deleteVideotapeSpaceInfoByNum(String channelID,String decrementStr){
        Result<Object> result = new Result<>();
        try {
            Integer decrement = 0; //压缩总量
            Integer num = 0; //压缩条数
            if(StringUtils.isNotBlank(decrementStr)){
                decrement = Integer.parseInt(decrementStr);
                num = decrement * 1024 / 500;
            }
            //查询要删除的文件路径
            List<ConfVideotapeSpace> confVideotapeSpaceList = confVideotapeSpaceService.getVideotapeSpaceInfoByNum(channelID,num);
            if(confVideotapeSpaceList != null && !confVideotapeSpaceList.isEmpty()){
                for (int i = 0;i<confVideotapeSpaceList.size();i++) {
                    String file_path = confVideotapeSpaceList.get(i).getFile_path();
                    File file = new File(file_path);
                    if(file.exists() && file.isFile()){
                        //删除文件
                        file.delete();
                    }
                }
            }

            //删除数据库文件记录
            confVideotapeSpaceService.deleteVideotapeSpaceInfoByNum(channelID,num);

        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 获取指定通道文件存储信息
     * @param channelID
     * @return Result
     */
    @PostMapping("/getChannelFileByChannelID")
    @ResponseBody
    @ApiOperation("获取指定通道文件存储信息")
    public Result<Object> getChannelFileByChannelID(String channelID){
        Result<Object> result = new Result<>();
        try {
            int num = confVideotapeSpaceService.getChannelSum(channelID);
            List<Integer> fileSizeList = confVideotapeSpaceService.getFileSizeList(channelID);
            String channelName = channelInfoService.getChannelName(channelID);
            Map map = new HashMap();
            map.put("channelName",channelName);
            map.put("channelTotalNum",num/1024);
            map.put("channelFileSizeList",fileSizeList);
            result.setResult(map);
        } catch (Exception e) {
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }
        return result;
    }

    /**
     * 获取所有通道文件存储信息
     * @return Result
     */
    @PostMapping("/getAllChannelFileInfo")
    @ResponseBody
    @ApiOperation("获取所有通道文件存储信息")
    public Result<Object> getAllChannelFileInfo(){
        Result<Object> result = new Result<>();
        try {
            List<Map<String,Object>> channelFileInfoList = confVideotapeSpaceService.getAllChannelFileInfo();
            if(channelFileInfoList != null && !channelFileInfoList.isEmpty()){
                for(int i = 0;i<channelFileInfoList.size();i++){
                    List<Integer> fileSizeList = confVideotapeSpaceService.getFileSizeList(channelFileInfoList.get(i).get("channel_id").toString());
                    String channelName = channelInfoService.getChannelName(channelFileInfoList.get(i).get("channel_id").toString());
                    channelFileInfoList.get(i).put("totalSpace",Integer.parseInt(channelFileInfoList.get(i).get("totalSpace").toString())/1024);
                    channelFileInfoList.get(i).put("channelName",channelName);
                    channelFileInfoList.get(i).put("fileSize",fileSizeList);
                }
            }

            result.setResult(channelFileInfoList);
        } catch (Exception e) {
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 统计目录文件个数
     * @param file_path
     */
    public int getTxtFilesCount(String file_path){
        int count = 0; //初始化统计变量
        File srcFile = new File(file_path);
        // 判断传入的文件是不是为空
        if (srcFile == null) {
            throw new NullPointerException();
        }
        // 把所有文件放入数组
        File[] files = srcFile.listFiles();
        if(files != null){
            // 遍历数组每一个元素
            for (File f : files) {
                // 判断文件是不是以.mp4结尾的文件，并且count++（文件要显示扩展名）
                if (f.getName().endsWith(".mp4")) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 检测一个目录下文件是否重复
     * @param k
     * @param file
     * @param filePathChannel
     * @param file_path
     * @param confVideotapeSpace
     * @return File
     */
    public File checkFileRepeat(int k,File file,String filePathChannel,String file_path,ConfVideotapeSpace confVideotapeSpace){
        if(file.exists()){
            k++;
            file_path = filePathChannel + "/" + confVideotapeSpace.getChannel_id() + "_" + k + ".mp4";
            file = new File(file_path);
            confVideotapeSpace.setFile_path(file_path);
            confVideotapeSpace.setFile_index(k);
            return checkFileRepeat(k,file,filePathChannel,file_path,confVideotapeSpace);
        }else{
            confVideotapeSpace.setFile_path(file_path);
            confVideotapeSpace.setFile_index(k);
            return file;
        }
    }
}
