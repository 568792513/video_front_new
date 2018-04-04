package com.hui.vedio_service.vedio.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hui.vedio_service.vedio.common.Result;
import com.hui.vedio_service.vedio.common.utils.CookieUtils;
import com.hui.vedio_service.vedio.entity.User;
import com.hui.vedio_service.vedio.entity.Vedio;
import com.hui.vedio_service.vedio.mapper.VedioMapper;
import com.hui.vedio_service.vedio.service.IVedioService;
import com.hui.vedio_service.vedio.service.UploadFeignService;
import com.hui.vedio_service.vedio.service.UserFeignService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2018-03-30
 */
@RestController
@RequestMapping("/api/web/video")
public class VedioController {

    @Value("${FtpVideoPath}")
    private String ftpVideoPath;

    @Value("${FtpVideoImgPath}")
    private String FtpVideoImgPath;

    @Resource
    private UserFeignService userFeignService;

    @Resource
    private UploadFeignService uploadFeignService;

    @Resource
    private IVedioService vedioService;

    @Resource
    private VedioMapper vedioMapper;

    /**
     * 添加一个新的视频
     * @param imgFile
     * @param videoFile
     * @param videoName
     * @param videoType
     * @param videoIntroduction
     * @param request
     * @return
     */
    @Transactional
    @PostMapping(value = "/addVideo")
    public Result addVideo(@RequestParam("imgFile") MultipartFile imgFile, @RequestParam("videoFile") MultipartFile videoFile,
                                @RequestParam("name") String videoName, @RequestParam("type") Integer videoType, @RequestParam("desc") String videoIntroduction,
                                HttpServletRequest request){
        Long userId = CookieUtils.getUserIdByToken(request);
        User user = userFeignService.getUserById(userId);
        if (user != null) {
            Vedio vedio = new Vedio();
            if (!StringUtils.isEmpty(videoName) && videoType != null && !StringUtils.isEmpty(videoIntroduction) &&
            !imgFile.isEmpty() && !videoFile.isEmpty()){
                vedio.setName(videoName);
                vedio.setType(videoType);
                vedio.setIntroduction(videoIntroduction);
                vedio.setSize((double) videoFile.getSize());
                vedio.setUserId(user.getId());

                // 先上传视频
                List<Vedio> userVideoList = vedioService.getAllVideoByUserId(user.getId());
                StringBuilder videoFileName = new StringBuilder();
                videoFileName.append("video_" + user.getId().toString() + "_rank_");
                if (userVideoList != null){
                    videoFileName.append( userVideoList.size() + ".mp4");
                } else {
                    videoFileName.append( 0 + ".mp4");
                }
                Boolean videoUploadFlag = uploadFeignService.uploadVideoFile(videoFile, videoFileName.toString());
                if (!videoUploadFlag) {
                    return new Result<>(Result.SERVER_ERROR, "视频上传失败", "");
                }
                vedio.setVideoFileInFtp(videoFileName.toString());
                // 存入数据库中的视频地址
                String newFileName = "http://" + ftpVideoPath + videoFileName.toString();
                vedio.setVedioUrl(newFileName);

                // 再上传封面
                StringBuilder imgFileName = new StringBuilder();
                imgFileName.append("videoImg_" + user.getId().toString() + "_rank_");
                if (userVideoList != null || userVideoList.size() > 0){
                    imgFileName.append( userVideoList.size() + ".jpg");
                } else {
                    imgFileName.append( 0 + ".jpg");
                }
                String ImgFileName = "videoImg_" + user.getId().toString() + "_rank_" + userVideoList.size() + ".jpg";
                Boolean videoImgUploadFlag = uploadFeignService.uploadFile(imgFile, ImgFileName);
                if (!videoImgUploadFlag) {
                    return new Result<>(Result.SERVER_ERROR, "封面图片上传失败", "");
                }
                vedio.setVideoImgInFtp(imgFileName.toString());
                // 存入数据库中的视频图片地址
                String newImgFileName = "http://" + FtpVideoImgPath + ImgFileName;
                vedio.setVedioImg(newImgFileName);
                Integer result = vedioMapper.insert(vedio);
                return result > 0 ? new Result<>(Result.SUCCESS, "上传成功", "") : new Result<>(Result.SERVER_ERROR, "入库失败", "" );

            }
            return new Result<>(Result.PARAM_ERROR, "检查参数是否为空", "");
        }
        return new Result<>(Result.PARAM_ERROR, "无法获取到用户", "");
    }

    /**
     * 我的视频页面获取我的视频
     * @param request
     * @return
     */
    @GetMapping(value = "/getMyVideo")
    public Result getMyVideo(Page<Vedio> page, HttpServletRequest request){
        Long userId = CookieUtils.getUserIdByToken(request);
        if (userId == null) {
            return new Result<>(Result.PARAM_ERROR, "无法获取到用户", "");
        }
        Page<Vedio> vedioPage = vedioService.selectVideoPage(page, userId);
        return new Result<>(Result.SUCCESS, "获取成功", "", vedioPage);
    }

    /**
     * 我的视频页面修改视频信息
     * @param id
     * @param name
     * @param introduction
     * @param type
     * @return
     */
    @PostMapping(value = "/editVideo")
    public Result editVideo(@RequestParam Long id, @RequestParam String name, @RequestParam String introduction,@RequestParam Integer type){
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(introduction) || id == null || type == null){
            return new Result<>(Result.PARAM_ERROR, "参数有空值", "");
        }
        Vedio vedioInDb = vedioMapper.selectById(id);
        if (vedioInDb == null){
            return new Result<>(Result.PARAM_ERROR, "无此视频", "");
        }
        vedioInDb.setName(name);
        vedioInDb.setIntroduction(introduction);
        vedioInDb.setType(type);
        // 更新库中记录
        Integer flag = vedioMapper.updateById(vedioInDb);
        return flag > 0 ? new Result<>(Result.SUCCESS, "修改成功", "") : new Result<>(Result.SERVER_ERROR, "服务器发生错误", "");
    }

    @Transactional
    @PostMapping(value = "/removeVideo")
    public Result removeVide(Vedio vedio){
        if (vedio == null || StringUtils.isEmpty(vedio.getId())){
            return new Result<>(Result.PARAM_ERROR, "参数有空值", "");
        }
        Vedio vedioInDb = vedioMapper.selectById(vedio.getId());

        //删除ftp上的文件
        Boolean removeVideoFileFlag = uploadFeignService.removeVideoFile(vedioInDb.getVideoFileInFtp());
        if (!removeVideoFileFlag){
            return new Result<>(Result.SERVER_ERROR, "删除服务器视频文件失败", "");
        }
        //删除ftp上的视频封面
        Boolean removeVideoImgFlag = uploadFeignService.removeVideoImg(vedioInDb.getVideoImgInFtp());
        if (!removeVideoImgFlag){
            return new Result<>(Result.SERVER_ERROR, "删除服务器视频文件封面失败", "");
        }

        //删除库中记录
        Integer flag = vedioMapper.deleteById(vedio.getId());
        if (flag <= 0){
            return new Result<>(Result.SERVER_ERROR, "服务器发生错误", "");
        }
        return new Result<>(Result.SUCCESS, "删除成功", "");
    }

}
