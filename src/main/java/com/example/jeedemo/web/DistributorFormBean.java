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
import com.example.jeedemo.domain.Distributor;
import com.example.jeedemo.service.DistributorManager;


@SessionScoped
@Named("distBean")
public class DistributorFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Distributor dist = new Distributor();
	
	private ListDataModel<Distributor> distrs = new ListDataModel<Distributor>();

	@Inject
	private DistributorManager distm;
	
	public Distributor getDist() {
		return dist;
	}

	public void setDist(Distributor dist) {
		this.dist = dist;
	}

	public ListDataModel<Distributor> getAllDistributors() {
		distrs.setWrappedData(distm.getAllDistributors());
		return distrs;
	}
	
	// Actions
	
	public String addDistributor() {
		distm.addDistributor(dist);
		//return "list";
		return "listDistributor";
	}
	
	public String makeEdit()
	{
		dist = distrs.getRowData();
		return "editDistributor";
	}
	
	public String editDistributor()
	{
		distm.editDistributor(dist);
		return "listDistributor";
	}
	
	public String removeDistributor() {
		Distributor distToDelete = distrs.getRowData();
		try
		{
			distm.deleteDistributor(distToDelete);
		}catch (Exception e){ }
		return null;
	}
	
	// Validators
	
	public void uniqueName(FacesContext context, UIComponent component,
			Object value) {

		String name = (String) value;

		for (Distributor d : distm.getAllDistributors()) {
			if (d.getName().equalsIgnoreCase(name)) {
				FacesMessage message = new FacesMessage( "Distributor with this name already exists in database");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}
}
