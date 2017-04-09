package com.zjt.entity;

import java.sql.Timestamp;

/**
 * AbstractComments entity provides the base persistence definition of the
 * Comments entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractComments implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer newsid;
	private Timestamp createTime;
	private Integer uerid;
	private String content;

	// Constructors

	/** default constructor */
	public AbstractComments() {
	}

	/** full constructor */
	public AbstractComments(Integer newsid, Timestamp createTime,
			Integer uerid, String content) {
		this.newsid = newsid;
		this.createTime = createTime;
		this.uerid = uerid;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNewsid() {
		return this.newsid;
	}

	public void setNewsid(Integer newsid) {
		this.newsid = newsid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getUerid() {
		return this.uerid;
	}

	public void setUerid(Integer uerid) {
		this.uerid = uerid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}