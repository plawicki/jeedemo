package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Developer;


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
}
