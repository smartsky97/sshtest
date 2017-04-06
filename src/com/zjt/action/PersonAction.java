package com.zjt.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zjt.entity.Person;
import com.zjt.service.PersonService;


public class PersonAction extends ActionSupport{
	private PersonService personService ;

	public PersonService getPersonService() {
		return personService;
	}
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	public String getPersonByID() {
		Person person = personService.getPersonById(1L);
		ServletActionContext.getRequest().setAttribute("person", person);
		System.out.println("PersonAction.getPersonByID()");
		return "index" ;
	}
}
