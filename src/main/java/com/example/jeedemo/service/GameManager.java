package com.example.jeedemo.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Developer;
import com.example.jeedemo.domain.Distributor;
import com.example.jeedemo.domain.Game;
import com.example.jeedemo.domain.Isgn;

@Stateless
public class GameManager {
	
	@PersistenceContext
	EntityManager em;
	
	String help = "";

	public void addGame(Game game, Long devId, Long[] distId, Long isgnId) {
		
		game.setDist(new ArrayList<Distributor>());
		game.setDev(null);
		game.setIsgn(null);
		
		for(Long i: distId)
		{
			Distributor dist = em.find(Distributor.class, i);
			
			game.getDist().add(dist);
			dist.getGames().add(game);
		}
		
		if(isgnId != null)
		{
			Isgn isgn = em.find(Isgn.class, isgnId);
			game.setIsgn(isgn);
		}
		
		if (devId != null) 
		{
			Developer dev = em.find(Developer.class, devId);
			game.setDev(dev);
		}
		
		game.setId(null);
		em.persist(game);
	}

	// Removes the game with given id
	public void deleteGame(Game game) {
		game = em.find(Game.class, game.getId());
		em.remove(game);
	}
	
	@SuppressWarnings("unchecked")
	public List<Game> getAllGames() {
		return em.createNamedQuery("game.all").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Game> getGamesByTitle(String arg)
	{
		this.help = arg;
		return em.createNamedQuery("game.find").setParameter("title", "%" + this.help + "%").getResultList();
	}
	
	public void editGame(Game gameToChange, Long devId, Long[] distId, Long isgnId)
	{
		gameToChange.setDist(new ArrayList<Distributor>());
		gameToChange.setDev(null);
		gameToChange.setIsgn(null);
		
		for(Long i: distId)
		{
			Distributor dist = em.find(Distributor.class, i);
			
			gameToChange.getDist().add(dist);
			dist.getGames().clear();
			dist.getGames().add(gameToChange);
		}
		
		if(isgnId != null)
		{
			Isgn isgn = em.find(Isgn.class, isgnId);
			gameToChange.setIsgn(isgn);
		}
		
		if (devId != null) 
		{
			Developer dev = em.find(Developer.class, devId);
			gameToChange.setDev(dev);
		}
		
		em.merge(gameToChange);
	}
}
