package com.zjt.entity;

/**
 * AbstractNewstype entity provides the base persistence definition of the
 * Newstype entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNewstype implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;

	// Constructors

	/** default constructor */
	public AbstractNewstype() {
	}

	/** full constructor */
	public AbstractNewstype(String name) {
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}