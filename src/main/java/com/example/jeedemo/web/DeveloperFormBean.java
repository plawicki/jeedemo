package com.example.jeedemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Developer;
import com.example.jeedemo.domain.Game;
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
		return "listDeveloper";
	}
	
	public String makeEdit()
	{
		dev = devs.getRowData();
		return "editDeveloper";
	}
	
	public String editDeveloper()
	{
		devm.editDeveloper(dev);
		return "listDeveloper";
	}
	
	public String removeDistributor() {
		Developer devToDelete = devs.getRowData();
		try{
		devm.deleteDeveloper(devToDelete);
		}catch (Exception e){ }
		return null;
	}
	
	// Validators
	
	public void uniqueName(FacesContext context, UIComponent component,
			Object value) {

		String name = (String) value;

		for (Developer d : devm.getAllDevelopers()) {
			if (d.getName().equalsIgnoreCase(name)) {
				FacesMessage message = new FacesMessage( "Developer with this name already exists in database");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}
}
