package com.zjt.entity;

import java.sql.Timestamp;

/**
 * Comments entity. @author MyEclipse Persistence Tools
 */
public class Comments extends AbstractComments implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Comments() {
	}

	/** full constructor */
	public Comments(Integer newsid, Timestamp createTime, Integer uerid,
			String content) {
		super(newsid, createTime, uerid, content);
	}

}
