package utils;

import java.util.regex.Pattern;

public class Regex {

	public static final String MAIL = "^[\\w-\\+]+(\\.[\\w-\\+]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static final String PASSWORD = "[A-ZÑa-zñ0-9%&#_-]{6,18}";

	public static final String NIF = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]";
		
	public static final String POSTAL_CODE = "^[\\d]{5}";
	public static final String POSTAL_NUMBER = "^[\\d]{1,3}[\\w]?";

	public static final String NAME = "^[A-ZÑ][áéíóúña-z ]+";
	public static final String SURNAMES = "^[A-ZÑ][áéíóúña-z ]+[ A-ZÑa-zñáéíóú]*";
	public static final String LOCATION_NAME = "^[A-ZÑ][A-ZÑáéíóúña-z ]+";
	public static final String STREET_NAME = "^[A-ZÑ][/áéíóúña-z ]+";

	public static boolean isValidFormat(String texto,  String regex) {
		return Pattern.compile(regex).matcher(texto).matches();
	}
	
} 