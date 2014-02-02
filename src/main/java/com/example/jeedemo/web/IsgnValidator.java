package com.example.jeedemo.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("isgnValidator")
public class IsgnValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		String isgn = (String) value;
		
		if (!isgn.matches("[0-9]{4}-[0-9]{2}-[0-9]{3}"))
		{
			FacesMessage message = new FacesMessage();
			message.setDetail("ISGN must match form xxxx-xx-xxx, where x is a number between 0 and 9");
			message.setSummary("ISGN must match form xxxx-xx-xxx, where x is a number between 0 and 9");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
