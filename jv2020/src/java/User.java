import java.time.LocalDate;

public class User {

	private String nif;
	private String name;
	private String surnames;
	private String address;
	private String mail;
	private String birthDate;
	private String registeredDate;
	private String password;
	private String role;

	public User(String nif, String name, String surnames
			, String address, String mail, String birthDate,
			String registeredDate, String password, String role) {
		this.nif = nif;
		this.name = name;
		this.surnames = surnames;
		this.address = address;
		this.mail = mail;
		this.birthDate = birthDate;
		this.registeredDate = registeredDate;
		this.password = password;
		this.role = role;
	}
	

	public User() {
		this.nif = new String("00000000T");
		this.name = new String("name");
		this.surnames = "surname surname";
		this.address = "address";
		this.mail = "mail@mail.com";
		this.birthDate = "2000-10-10";
		this.registeredDate = LocalDate.now().toString();
		this.password = "Miau#0";
		this.role = "GUEST";
	}


	public String getNif() {
		return nif;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurnames() {
		return surnames;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public String getRegisteredDate() {
		return registeredDate;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setNif(String nif) {	
		this.nif = nif;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

}