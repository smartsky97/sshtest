package com.zjt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Person entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "person", catalog = "sshtest")
public class Person implements java.io.Serializable {

	// Fields

	private Long pid;
	private Sex sex;
	private String name;
	private String description;

	// Constructors

	/** default constructor */
	public Person() {
	}

	/** minimal constructor */
	public Person(Long pid) {
		this.pid = pid;
	}

	/** full constructor */
	public Person(Long pid, Sex sex, String name, String description) {
		this.pid = pid;
		this.sex = sex;
		this.name = name;
		this.description = description;
	}

	// Property accessors
	@Id
	@Column(name = "pid", unique = true, nullable = false)
	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sid")
	public Sex getSex() {
		return this.sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 50)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}