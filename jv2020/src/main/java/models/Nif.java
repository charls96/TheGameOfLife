package models;

import utils.Regex;

public class Nif {

	private String text;

	public Nif(String text) {
		this.setText(text);
	}

	public Nif() {
		this.setText("00000000T");
	}

	public Nif(Nif nif) {
		this.text = new String(nif.text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		assert text != null;
		if (isValidNif(text)) {
			this.text = text;
		}
	}

	private boolean isValidNif(String text) {
		return text.matches(Regex.NIF)
				&& isValidLetter(text);   
	}

	private boolean isValidLetter(String text) {	
		int value = Integer.parseInt(text.substring(0, 8));	
		return "TRWAGMYFPDXBNJZSQVHLCKE".charAt(value % 23) == text.charAt(8);
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
		Nif other = (Nif) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public Nif clone() {
		return  new Nif(this);
	}




}
