package com.zjt.dao;

import java.io.Serializable;

import com.zjt.entity.Person;

public interface PersonDao {
	public Person getPersonById(Serializable id);
}
