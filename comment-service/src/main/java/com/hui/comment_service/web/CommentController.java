package com.hui.comment_service.web;


import com.hui.comment_service.common.Result;
import com.hui.comment_service.common.utils.CookieUtils;
import com.hui.comment_service.entity.Comment;
import com.hui.comment_service.entity.User;
import com.hui.comment_service.service.CommentService;
import com.hui.comment_service.service.UploadFeignService;
import com.hui.comment_service.service.UserFeignService;
import com.hui.comment_service.service.VideoFeignService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
@RequestMapping("/api/web/comment")
public class CommentController {

    @Resource
    private UserFeignService userFeignService;

    @Resource
    private UploadFeignService uploadFeignService;

    @Resource
    private VideoFeignService videoFeignService;

    @Resource
    private CommentService commentService;

    @Transactional
    @PostMapping(value = "addComment")
    public Result addComment(Long videoId, String content, HttpServletRequest request){
        if (StringUtils.isEmpty(videoId) || StringUtils.isEmpty(content) || request == null){
            return new Result(Result.PARAM_ERROR, "参数有空值");
        }
        Long userId = CookieUtils.getUserIdByToken(request);
        User user = userFeignService.getUserById(userId);
        if (user == null){
            return new Result(Result.PARAM_ERROR, "不能获取到用户");
        }
        List<Comment> commentList = commentService.getCommentByVideoId(videoId);
        Comment comment = new Comment();
        comment.setVideoId(videoId);
        comment.setUser(user);
        comment.setContent(content);
        comment.setTime(new Date());
        comment.setFloor(commentList.size() + 1);
        commentService.save(comment);
        // 更新mysql表
        videoFeignService.addCommentCount(videoId);
        return new Result(Result.SUCCESS, "评论成功");
    }

    @GetMapping(value = "getComment")
    public Result getComment(Long videoId, Integer current, Integer size){
        if (StringUtils.isEmpty(videoId) || current==null || size==null){
            return new Result(Result.PARAM_ERROR, "参数有空值");
        }
        Pageable pageable = new PageRequest(current - 1, size);
        Page<Comment> pageResult = commentService.getAllByVideoId(videoId, pageable);
        if (pageResult == null || pageResult.getTotalPages() == 0){
            return new Result<>(Result.SUCCESS, "无评论", "", 0);
        }
        return new Result<>(Result.SUCCESS, "", "", pageResult);
    }

}
