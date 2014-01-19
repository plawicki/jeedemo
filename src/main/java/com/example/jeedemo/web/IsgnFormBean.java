package com.example.jeedemo.web;

import java.beans.Expression;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

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
		return null;
	}
	
	public String makeEdit()
	{
		isgn = isgns.getRowData();
		return "edit";
	}
	
	public String editIsgn()
	{
		isgnm.editIsgn(isgn);
		return "list";
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
}
