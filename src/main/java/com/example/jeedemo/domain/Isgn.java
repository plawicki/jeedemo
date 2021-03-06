package com.example.jeedemo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "isgn.all", query = "Select ign from Isgn ign"),
	@NamedQuery(name = "isgn.free", query = "Select ign from Isgn ign where ign.id NOT IN (Select gm.isgn.id from Game gm)")
})
public class Isgn {
	
	private Long id; 
	
	String value;
	
	Game game;

	@OneToOne(optional=true, mappedBy="isgn", cascade= { CascadeType.DETACH })
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
