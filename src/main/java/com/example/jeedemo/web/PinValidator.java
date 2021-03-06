package com.example.jeedemo.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("pinValidator")
public class PinValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		String pesel = (String) value;
		
		if (pesel.length() != 8) {
			FacesMessage message = new FacesMessage();
			message.setDetail("PIN musi składać się z 8 cyfr");
			message.setSummary("PIN musi składać się z 8 cyfr");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
