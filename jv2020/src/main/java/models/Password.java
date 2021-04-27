package models;

import utils.Cryptography;
import utils.Regex;

public class Password {

	private String text;

	public Password(String text) {
		this.setText(text);
	}

	public Password() {
		this.setText("Miau#00");
	}

	public Password(Password password) {
		this.text = new String(password.text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (isValidPassword(text)) {
			this.text = Cryptography.cesar(text);
		}
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
