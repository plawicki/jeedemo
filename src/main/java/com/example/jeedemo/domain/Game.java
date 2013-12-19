package com.example.jeedemo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "game.all", query = "Select g from Game g")
})
public class Game {
	
	private Long id;

	String PIN;
	String title;
	Date yop;
	double price;
	boolean requireAdult;
	
	public Game() {

	}

	public Game(String PIN, String title, Date yop, double price, boolean requireAdult) {
		super();
		this.PIN = PIN;
		this.title = title;
		this.yop = yop;
		this.price = price;
		this.requireAdult = requireAdult;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Size(min = 8)
	public String getPIN() {
		return PIN;
	}

	public void setPIN(String PIN) {
		this.PIN = PIN;
	}

	@Size(min = 2, max = 20)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getYop() {
		return yop;
	}
	public void setYop(Date yop) {
		this.yop = yop;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isRequireAdult() {
		return requireAdult;
	}
	public void setRequireAdult(boolean requireAdult) {
		this.requireAdult = requireAdult;
	}
	
	

}
