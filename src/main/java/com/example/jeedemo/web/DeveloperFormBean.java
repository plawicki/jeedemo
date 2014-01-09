package com.example.jeedemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Developer;
import com.example.jeedemo.service.DeveloperManager;


@SessionScoped
@Named("devBean")
public class DeveloperFormBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Developer dev = new Developer();
	
	private ListDataModel<Developer> devs = new ListDataModel<Developer>();

	@Inject
	private DeveloperManager devm;
	
	public Developer getDev() {
		return dev;
	}

	public void setDev(Developer dev) {
		this.dev = dev;
	}

	public ListDataModel<Developer> getAllDevelopers() {
		devs.setWrappedData(devm.getAllDevelopers());
		return devs;
	}
	
	// Actions
	
	public String addDeveloper() {
		devm.addDeveloper(dev);
		//return "list";
		return null;
	}
}
