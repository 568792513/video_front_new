package com.hui.vedio_service.vedio.entity;


import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
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
@TableName("t_vedio")
public class Vedio extends LogicOpEntity<Vedio> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	@TableField("vedio_img")
	private String vedioImg;
	@TableField("vedio_url")
	private String vedioUrl;
	@TableField("user_id")
	private Long userId;
	private Integer type;
	private Integer star;
	private Double size;
	@TableField("length_time")
	private Date lengthTime;
	private String introduction;
	@TableField("play_amount")
	private Integer playAmount;
	@TableField("comment_amount")
	private Integer commentAmount;
	private String remark;


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

	public String getVedioImg() {
		return vedioImg;
	}

	public void setVedioImg(String vedioImg) {
		this.vedioImg = vedioImg;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
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

	public void setIntroduction(String Introduction) {
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Vedio{" +
			"id=" + id +
			", name=" + name +
			", vedioImg=" + vedioImg +
			", vedioUrl=" + vedioUrl +
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
