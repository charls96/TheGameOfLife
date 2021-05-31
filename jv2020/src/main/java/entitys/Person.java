package entitys;

import java.io.Serializable;

import utils.EasyDate;

public class Person implements Serializable {
	protected Nif nif;
	protected String name;
	protected String surnames;
	protected Address address;
	protected Mail mail;
	protected EasyDate birthDate;
	
	public Person(Nif nif, String name, String surnames, 
			Address address, Mail mail, EasyDate birthDate) {
		this.nif = nif;
		this.name = name;
		this.surnames = surnames;
		this.address = address;
		this.mail = mail;
		this.birthDate = birthDate;
	}

	public Person() {
		this.nif = new Nif();
		this.name = new String();
		this.surnames = new String();
		this.address = new Address();
		this.mail = new Mail();	
		this.birthDate = EasyDate.today();
	}
	
	public Person(Person person) {
		assert person != null;
		this.nif = new Nif(person.nif);
		this.name = new String(person.name);
		this.surnames = new String(person.surnames);
		this.address = new Address(person.address);
		this.mail = new Mail(person.mail);	
		this.birthDate = person.birthDate.clone();	
	}
	
	public Nif getNif() {
		return this.nif;
	}

	public String getName() {
		return this.name;
	}

	public String getSurnames() {
		return this.surnames;
	}

	public Address getAddress() {
		return this.address;
	}

	public Mail getMail() {
		return this.mail;
	}

	public EasyDate getBirthDate() {
		return this.birthDate;
	}

	public void setNif(Nif nif) {	
		assert nif != null;
		this.nif = nif;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public void setBirthDate(EasyDate birthDate) {
		assert birthDate != null;

		if (isValidBirthDate(birthDate)) {
			this.birthDate = birthDate;
			return;
		}

		throw new ModelsException("birthDate: no válida");
	}

	private boolean isValidBirthDate(EasyDate birthDate) {
		return birthDate.isBefore(EasyDate.now().minusYears(16));
	}

	
}
