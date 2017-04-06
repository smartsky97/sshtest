package com.zjt.service.impl;

import java.io.Serializable;

import com.zjt.dao.PersonDao;
import com.zjt.entity.Person;
import com.zjt.service.PersonService;

public class PersonServiceImpl implements PersonService{
	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public Person getPersonById(Serializable id) {
		// TODO Auto-generated method stub
		Person person =  this.personDao.getPersonById(id);
		return person;
	}
}
