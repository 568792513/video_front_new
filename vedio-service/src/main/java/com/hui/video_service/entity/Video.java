package com.hui.video_service.entity;


import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2018-03-30
 */
@TableName("t_video")
public class Video extends LogicOpEntity<Video> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	@TableField("video_img")
	private String videoImg;
	@TableField("video_url")
	private String videoUrl;
	@TableField("user_id")
	private Long userId;
	private Integer type;
	private Integer star;
	private Double size;
	@TableField("length_time")
	private Date lengthTime;
	@TableField("introduction")
	private String introduction;
	@TableField("play_amount")
	private Integer playAmount;
	@TableField("comment_amount")
	private Integer commentAmount;
	private String remark;
	@TableField("video_img_in_ftp")
	private String videoImgInFtp;
	@TableField("video_file_in_ftp")
	private String videoFileInFtp;
	private String format;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVideoImg() {
		return videoImg;
	}

	public void setVideoImg(String videoImg) {
		this.videoImg = videoImg;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Date getLengthTime() {
		return lengthTime;
	}

	public void setLengthTime(Date lengthTime) {
		this.lengthTime = lengthTime;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getPlayAmount() {
		return playAmount;
	}

	public void setPlayAmount(Integer playAmount) {
		this.playAmount = playAmount;
	}

	public Integer getCommentAmount() {
		return commentAmount;
	}

	public void setCommentAmount(Integer commentAmount) {
		this.commentAmount = commentAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVideoImgInFtp() {
		return videoImgInFtp;
	}

	public void setVideoImgInFtp(String videoImgInFtp) {
		this.videoImgInFtp = videoImgInFtp;
	}

	public String getVideoFileInFtp() {
		return videoFileInFtp;
	}

	public void setVideoFileInFtp(String videoFileInFtp) {
		this.videoFileInFtp = videoFileInFtp;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "video{" +
			"id=" + id +
			", name=" + name +
			", videoImg=" + videoImg +
			", videoUrl=" + videoUrl +
			", userId=" + userId +
			", type=" + type +
			", star=" + star +
			", size=" + size +
			", lengthTime=" + lengthTime +
			", introduction=" + introduction +
			", playAmount=" + playAmount +
			", commentAmount=" + commentAmount +
			", remark=" + remark +
			"}";
	}
}
