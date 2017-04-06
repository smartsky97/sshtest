package com.zjt.service;

import java.io.Serializable;

import com.zjt.entity.Person;

public interface PersonService {
	public Person getPersonById(Serializable id);
}
