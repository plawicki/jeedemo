package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Game;
import com.example.jeedemo.domain.Person;
import com.example.jeedemo.service.GameManager;
import com.example.jeedemo.service.IsgnManager;


@SessionScoped
@Named("gameBean")
public class GameFormBean implements Serializable 
{

	private static final long serialVersionUID = 1L;

	private Game game = new Game();

	private ListDataModel<Game> games = new ListDataModel<Game>();
	
	private Long[] distId = new Long[1];
	private Long devId;
	private Long isgnId;
	
	private String findTitle = "";

	public String getFindTitle() {
		return findTitle;
	}

	public void setFindTitle(String findTitle) {
		this.findTitle = findTitle;
	}

	public Long getIsgnId() {
		return isgnId;
	}

	public void setIsgnId(Long isgnId) {
		this.isgnId = isgnId;
	}

	public Long[] getDistId() {
		return distId;
	}

	public void setDistId(Long[] distId) {
		this.distId = distId;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	@Inject
	private GameManager gm;
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ListDataModel<Game> getAllGames() {
		games.setWrappedData(gm.getAllGames());
		return games;
	}

	// Actions
	
	public String addGame() {

		gm.addGame(game, devId, distId, isgnId);
		return "listGame";
	}
	
	public String makeEdit()
	{
		game = games.getRowData();
		return "editGame";
	}
	
	public String editGame()
	{
		gm.editGame(game, devId, distId, isgnId);
		return "listGame";
	}

	public String deleteGame() {
		Game gameToDelete = games.getRowData();
		
		gm.deleteGame(gameToDelete);
		return null;
	}
	
	public ListDataModel<Game> getGamesByTitle()
	{
		games.setWrappedData(gm.getGamesByTitle(findTitle));
		return games;
	}
	
	// Validators
	
	public void uniquePin(FacesContext context, UIComponent component,
			Object value) {

		String PIN = (String) value;

		for (Game g : gm.getAllGames()) {
			if (g.getPIN().equalsIgnoreCase(PIN)) {
				FacesMessage message = new FacesMessage( "Game with this PIN already exists in database");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}

	// xxxx-year
	public void validatePinYop(ComponentSystemEvent event) {
		
		UIForm form = (UIForm) event.getComponent();
		UIInput PIN = (UIInput) form.findComponent("PIN");
		UIInput yop = (UIInput) form.findComponent("yop");

		if (PIN.getValue() != null && yop.getValue() != null
		 && PIN.getValue().toString().length() >= 4) 
		
		{															//12051992
			String fourDigitsOfPin = PIN.getValue().toString().substring(4, 8);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(((Date) yop.getValue()));

			String DigitsOfYear = ((Integer) cal.get(Calendar.YEAR))
					.toString();

			if (!fourDigitsOfPin.equals(DigitsOfYear)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(form.getClientId(), new FacesMessage(
						"PIN doesn't match date of publication"));
				context.renderResponse();
			}
		}
	}
}
