package entityes;

import java.io.Serializable;

import utils.Cryptography;
import utils.Regex;
import jLife.Configuration;

public class Password implements Serializable {

	private static final long serialVersionUID = 1L;
	private String text;

	public Password(String text) throws EntitysException {
		this.setText(text);
	}

	public Password() throws EntitysException {
		this.setText(Configuration.get().getProperty("password.default"));
	}

	public Password(Password password) {
		this.text = new String(password.text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) throws EntitysException {
		assert text != null;
		if (isValidPassword(text)) {
			this.text = Cryptography.cesar(text);
			return;
		}	
		throw new EntitysException("Formato no válido.");
	}
	
	public void setEncriptedText(String encriptedText) {
		assert encriptedText != null;
		this.text = encriptedText;
		
	}
	
	private boolean isValidPassword(String text) {
		return text.matches(Regex.PASSWORD);        
	}


	@Override
	public String toString() {
		return String.format("%s", text);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Password other = (Password) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public Password clone() {
		return  new Password(this);
	}


}
