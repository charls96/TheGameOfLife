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
		this.setNif(nif);
		this.setName(name);
		this.setSurnames(surnames);
		this.setAddress(address);
		this.setMail(mail);
		this.setBirthDate(birthDate);
		this.setRegisteredDate(registeredDate);
		this.setPassword(password);
		this.setRole(role);
	}
	
	public User() {
		this(new String("00000000T"),
				new String("name"),
				"surname surname",
				"address",
				"mail@mail.com",
				"2000-10-10",
				LocalDate.now().toString(),
				"Miau#0",
				"GUEST"
				);
	}

	public User(User usuario) {
		
		assert usuario != null;
		
		this.nif = new String(usuario.nif);
		this.name = new String(usuario.name);
		this.surnames = new String(usuario.surnames);
		this.address = new String(usuario.address);
		this.mail = new String(usuario.mail);
		this.birthDate = new String(usuario.birthDate);
		this.registeredDate = new String(usuario.registeredDate);
		this.password = new String(usuario.password);
		this.role = new String(usuario.role);	
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
		
		assert nif != null;
		
		if (isValidNif(nif)) {
			this.nif = nif;
		}
	}

	private boolean isValidNif(String nif) {
		
		// TO-DO lógica validación
		
		return true;
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
		
		assert birthDate != null;
		
		if (isValidBirthDate()) {
			this.birthDate = birthDate;
		}
	}

	private boolean isValidBirthDate() {
		
		// TO-DO lógica validación
		
		return true;
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

	@Override
	public User clone() {
		return new User(this);
	}

	@Override
	public String toString() {
		return "User [nif=" + nif + ", name=" + name + ", surnames=" + surnames + ", address=" + address + ", mail="
				+ mail + ", birthDate=" + birthDate + ", registeredDate=" + registeredDate + ", password=" + password
				+ ", role=" + role + "]";
	}
	
	
}