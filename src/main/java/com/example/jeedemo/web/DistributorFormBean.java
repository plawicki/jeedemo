package com.example.jeedemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
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
		return null;
	}
	
	public String makeEdit()
	{
		dist = distrs.getRowData();
		return "edit";
	}
	
	public String editDistributor()
	{
		distm.editDistributor(dist);
		return "list";
	}
	
	public String removeDistributor() {
		Distributor distToDelete = distrs.getRowData();
		try
		{
		distm.deleteDistributor(distToDelete);
		}catch (Exception e){ }
		return null;
	}
}
