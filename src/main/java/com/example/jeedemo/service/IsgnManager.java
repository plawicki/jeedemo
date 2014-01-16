package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Game;
import com.example.jeedemo.domain.Isgn;


	@Stateless
	public class IsgnManager {

		@PersistenceContext
		EntityManager em;
		
		public void addIsgn(Isgn isgn) {

			isgn.setId(null);
			em.persist(isgn);
		}
		
		@SuppressWarnings("unchecked")
		public List<Isgn> getAllIsgns() {
			return em.createNamedQuery("isgn.all").getResultList();
		}
		
		// Removes the isgn with given Title
		public void deleteIsgn(Isgn isgn) {
			isgn = em.find(Isgn.class, isgn.getId());
			em.remove(isgn);
		}
	}
