package com.example.jeedemo.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("titleValidator")
public class TitleValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		String title = (String) value;
		
		if (title.length() >= 16) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Tytuł nie może być większy niż 16 znaków");
			message.setSummary("Tytuł nie może być większy niż 16 znaków");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
