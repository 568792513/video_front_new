package com.hui.comment_service.service;

import com.hui.comment_service.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface CommentService extends MongoRepository<Comment, Long> {
    Page<Comment> getAllByVideoId(Long videoId, Pageable pageable);
    List<Comment> getCommentByVideoId(Long videoId);
//    Page<Comment> queryFirstByVideoId(Long videoId);
}
