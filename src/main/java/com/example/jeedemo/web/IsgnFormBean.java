package com.example.jeedemo.web;

import java.beans.Expression;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Distributor;
import com.example.jeedemo.domain.Isgn;
import com.example.jeedemo.service.IsgnManager;


@SessionScoped
@Named("isgnBean")
public class IsgnFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Isgn isgn = new Isgn();
	
	private ListDataModel<Isgn> isgns = new ListDataModel<Isgn>();

	@Inject
	private IsgnManager isgnm;

	public Isgn getIsgn() {
		return isgn;
	}

	public void setIsgn(Isgn isgn) {
		this.isgn = isgn;
	}

	public ListDataModel<Isgn> getAllIsgns() {
		isgns.setWrappedData(isgnm.getAllIsgns());
		return isgns;
	}
	
	public ListDataModel<Isgn> getFreeIsgns()
	{
		isgns.setWrappedData(isgnm.getFreeIsgns());
		return isgns;
	}
	
	// Actions
	
	public String addIsgn() {
		isgnm.addIsgn(isgn);
		//return "list";
		return "listISGN";
	}
	
	public String makeEdit()
	{
		isgn = isgns.getRowData();
		return "editISGN";
	}
	
	public String editIsgn()
	{
		isgnm.editIsgn(isgn);
		return "listISGN";
	}
	
	public String removeIsgn()
	{
		try
		{
		isgnm.deleteIsgn(isgn);
		} catch (Exception e)
		{
			System.out.println("NOPE");
		}
		
		return null;
	}
	
	// Validators
	
	public void uniqueName(FacesContext context, UIComponent component,
			Object value) {

		String name = (String) value;

		for (Isgn i : isgnm.getAllIsgns()) {
			if (i.getValue().equalsIgnoreCase(name)) {
				FacesMessage message = new FacesMessage( "ISGN with this value already exists in database");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}
}
