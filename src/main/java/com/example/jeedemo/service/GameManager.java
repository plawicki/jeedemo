package com.example.jeedemo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Game;
import com.example.jeedemo.domain.Person;

@Stateless
public class GameManager {
	
	@PersistenceContext
	EntityManager em;

	public void addGame(Game game) {

		game.setId(null);
		em.persist(game);
	}

	// Removes the game with given Title
	public void deleteGame(Game game) {
		game = em.find(Game.class, game.getId());
		em.remove(game);
	}

	@SuppressWarnings("unchecked")
	public List<Game> getAllGames() {
		return em.createNamedQuery("game.all").getResultList();
	}
	
	/*
	// Games with adult requirments
	public List<Game> getGamesByAdult()
	{
		List<Game> gameByAdult = new ArrayList<Game>();
		for(Game g : db)
		{
			if(g.isRequireAdult())
				gameByAdult.add(g);
		}
		return gameByAdult;
	}
	
	// Games without adult requirments
	public List<Game> getGamesByNotAdult()
	{
		List<Game> gameByNotAdult = new ArrayList<Game>();
		
		for (Game g : db) {
			if (!g.isRequireAdult()) {
				gameByNotAdult.add(g);
			}
		}
		return gameByNotAdult;

	}
	
	// Find game with given title
	public Game getGameByTitle(Game game) {

		for (Game g : db) {
			if (game.getTitle().equals(g.getTitle())) {
				return g;
			}
		}

		return null;
	}
	
	// Find game with given PIN
		public Game getGamePIN(Game game) {

			for (Game g : db) {
				if (game.getPIN().equals(g.getPIN())) {
					return g;
				}
			}

			return null;
		}
	
	// Games by year of publication
	public List<Game> getGamesByYop(Date yop)
	{
		List<Game> gameByYop = new ArrayList<Game>();
		for(Game g : db)
		{
			if(g.getYop().equals(yop))
				gameByYop.add(g);
		}
		return gameByYop;
	}
	
	// Games by price
	public List<Game> getGamesByPrice(double price)
	{
		List<Game> gameByPrice = new ArrayList<Game>();
		for(Game g : db)
		{
			if(g.getPrice() == price)
				gameByPrice.add(g);
		}
		return gameByPrice;
	}
	
	*/
}
