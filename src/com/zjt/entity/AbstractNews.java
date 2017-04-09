package com.zjt.entity;

import java.sql.Timestamp;

/**
 * AbstractNews entity provides the base persistence definition of the News
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNews implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private Timestamp createTime;
	private Integer typeId;
	private Integer byAuthor;
	private Integer clickSum;

	// Constructors

	/** default constructor */
	public AbstractNews() {
	}

	/** full constructor */
	public AbstractNews(String title, String content, Timestamp createTime,
			Integer typeId, Integer byAuthor, Integer clickSum) {
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.typeId = typeId;
		this.byAuthor = byAuthor;
		this.clickSum = clickSum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getByAuthor() {
		return this.byAuthor;
	}

	public void setByAuthor(Integer byAuthor) {
		this.byAuthor = byAuthor;
	}

	public Integer getClickSum() {
		return this.clickSum;
	}

	public void setClickSum(Integer clickSum) {
		this.clickSum = clickSum;
	}

}