package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Developer;
import com.example.jeedemo.domain.Game;


@Stateless
public class DeveloperManager {

	@PersistenceContext
	EntityManager em;
	
	public void addDeveloper(Developer dev) {

		dev.setId(null);
		em.persist(dev);
	}
	
	@SuppressWarnings("unchecked")
	public List<Developer> getAllDevelopers() {
		return em.createNamedQuery("dev.all").getResultList();
	}
	
	public void deleteDeveloper(Developer dev) {
		dev = em.find(Developer.class, dev.getId());
		em.remove(dev);
	}
	
	public void editDeveloper(Developer dev)
	{
		em.merge(dev);	
	}
}
