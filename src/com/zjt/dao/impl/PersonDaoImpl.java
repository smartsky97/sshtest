package com.zjt.dao.impl;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zjt.dao.PersonDao;
import com.zjt.entity.Person;

public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao{

	@Override
	public Person getPersonById(Serializable id) {
		Person person = this.getHibernateTemplate().load(Person.class, id);
		return person;
	}
}
