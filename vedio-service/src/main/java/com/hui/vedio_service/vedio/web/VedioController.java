package com.hui.vedio_service.vedio.web;


import com.hui.vedio_service.vedio.common.Result;
import com.hui.vedio_service.vedio.common.utils.CookieUtils;
import com.hui.vedio_service.vedio.entity.User;
import com.hui.vedio_service.vedio.entity.Vedio;
import com.hui.vedio_service.vedio.mapper.VedioMapper;
import com.hui.vedio_service.vedio.service.IVedioService;
import com.hui.vedio_service.vedio.service.UploadFeignService;
import com.hui.vedio_service.vedio.service.UserFeignService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @Resource
    private UserFeignService userFeignService;

    @Resource
    private UploadFeignService uploadFeignService;

    @Resource
    private IVedioService vedioService;

    @Resource
    private VedioMapper vedioMapper;

    @Transactional
    @PostMapping(value = "/addVideo")
    public Result uploadHeadImg(@RequestParam("imgFile") MultipartFile imgFile, @RequestParam("videoFile") MultipartFile videoFile,
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
                // 先上传封面
                String ImgFileName = "videoImg_" + user.getId().toString() + ".jpg";
                Boolean videoImgUploadFlag = uploadFeignService.uploadFile(imgFile, ImgFileName);
                if (videoImgUploadFlag){
                    // 存入数据库中的视频图片地址
                    String newImgFileName = "http://" + ftpVideoPath + ImgFileName;
                    vedio.setVedioImg(newImgFileName);
                    List<Vedio> userVideoList = vedioService.getAllVideoByUserId(user.getId());
                    StringBuilder videoFileName = new StringBuilder();
                    videoFileName.append("video_" + user.getId().toString() + "_rank_");
                    // 再上传视频
                    if (userVideoList != null){
                        videoFileName.append( userVideoList.size() + ".mp4");
                    } else {
                        videoFileName.append( 0 + ".mp4");
                    }
                    Boolean videoUploadFlag = uploadFeignService.uploadVideoFile(videoFile, videoFileName.toString());
                    if (videoUploadFlag) {
                        // 存入数据库中的视频地址
                        String newFileName = "http://" + ftpVideoPath + videoFileName.toString();
                        vedio.setVedioUrl(newFileName);
                        // 入库
                        Integer result = vedioMapper.insert(vedio);
                        return result > 0 ? new Result<>(Result.SUCCESS, "上传成功", "") : new Result<>(Result.SERVER_ERROR, "入库失败", "" );
                    } else {
                        return new Result<>(Result.SERVER_ERROR, "视频上传失败", "");
                    }
                } else {
                    return new Result<>(Result.SERVER_ERROR, "封面图片上传失败", "");
                }
            }
            return new Result<>(Result.PARAM_ERROR, "检查参数是否为空", "");
        }
        return new Result<>(Result.PARAM_ERROR, "无法获取到用户", "");
    }
}
