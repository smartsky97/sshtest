package com.zjt.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Sex entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sex", catalog = "sshtest")
public class Sex implements java.io.Serializable {

	// Fields

	private Long sid;
	private String name;
	private Set<Person> persons = new HashSet<Person>(0);

	// Constructors

	/** default constructor */
	public Sex() {
	}

	/** minimal constructor */
	public Sex(Long sid) {
		this.sid = sid;
	}

	/** full constructor */
	public Sex(Long sid, String name, Set<Person> persons) {
		this.sid = sid;
		this.name = name;
		this.persons = persons;
	}

	// Property accessors
	@Id
	@Column(name = "sid", unique = true, nullable = false)
	public Long getSid() {
		return this.sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sex")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

}