package com.hui.comment_service.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long videoId;
    private User user;
    private Date time;
    private String content;
    private Integer floor;


    public Comment() {
    }
}
