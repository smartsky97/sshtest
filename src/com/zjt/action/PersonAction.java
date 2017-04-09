package com.zjt.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.zjt.entity.Person;
import com.zjt.service.PersonService;

@Controller
public class PersonAction extends BaseAction{
	@Autowired
	private PersonService personService ;



	public String getPersonByID() {
		Person person = personService.getPersonById(1L);
		ServletActionContext.getRequest().setAttribute("person", person);
		System.out.println("PersonAction.getPersonByID()");
		return "index" ;
	}
}
