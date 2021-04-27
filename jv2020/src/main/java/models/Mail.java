package models;

import utils.Regex;

public class Mail {
	
	private String text;
	
	public Mail(String text) {
		setText(text);
	}

	public Mail() {
		this("mail@mail.com");
	}

	public Mail(Mail mail) {
		this.text = new String(mail.text);
	}
	
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		assert text != null;
		if (isValidMail(text)) {
			this.text = text;
		}
	}

	private boolean isValidMail(String text) {
		return text.matches(Regex.MAIL);
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
		Mail other = (Mail) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public Mail clone() {
		return new Mail(this);
	}
	
}
