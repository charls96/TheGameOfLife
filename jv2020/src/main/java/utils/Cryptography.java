package utils;

public class Cryptography {

	public static String cesar(String plainText) {
		String normalAlphabet =  "AaBbCcDdEeFfGgHhIiJjKkLlMmNnÑñOoPpQqRrSsTtUuVvXxYyZz0123456789!?$%&/#";
		String shiftedAlphabet = "EeFfGgHhIiJjKkLlMmNnÑñOoPpQqRrSsTtUuVvXxYyZz0123456789!?$%&/#AaBbCcDd";
		char characterToEncrypt;
		int characterToEncryptPosition;
		StringBuilder  encryptedText = new StringBuilder();

		for (int i=0; i < plainText.length(); i++){
			characterToEncrypt = plainText.charAt(i);
			characterToEncryptPosition = normalAlphabet.indexOf(characterToEncrypt);
			encryptedText.append(shiftedAlphabet.charAt(characterToEncryptPosition));

		}
		return encryptedText.toString();
	}
	
}
