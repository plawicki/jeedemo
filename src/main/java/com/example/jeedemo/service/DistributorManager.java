package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Distributor;


@Stateless
public class DistributorManager {

	@PersistenceContext
	EntityManager em;
	
	public void addDistributor(Distributor dist) {

		dist.setId(null);
		em.persist(dist);
	}
	
	@SuppressWarnings("unchecked")
	public List<Distributor> getAllDistributors() {
		return em.createNamedQuery("dist.all").getResultList();
	}
	
}
