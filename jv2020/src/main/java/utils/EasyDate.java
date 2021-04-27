package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class EasyDate {

	private final LocalDateTime localDateTime;

	public static EasyDate now() {
		return new EasyDate();
	}
	
	public static EasyDate today() {
		return new EasyDate(LocalDate.now(), LocalTime.of(0, 0, 0));
	}
	
	public EasyDate() {
		this.localDateTime = LocalDateTime.now();
	}

	public EasyDate(int year, int month, int day) {
		this(LocalDate.of(year, month, day), LocalTime.of(0, 0, 0));
	}
	
	public EasyDate(int year, int month, int day, int hour, int minute, int seconde) {
		this(LocalDate.of(year, month, day), LocalTime.of(hour, minute, seconde));
	}
	
	public EasyDate(String date) {
		assert date != null;
		String[] partes = date.split("[/-.]");	
		int year = Integer.parseInt(partes[0]);
		int month = Integer.parseInt(partes[1]);
		int day = Integer.parseInt(partes[2]);
		this.localDateTime = LocalDateTime.of(year, month, day, 0 , 0 , 0);
	}
	
	private EasyDate(LocalDate localDate, LocalTime localTime) {
		this.localDateTime = LocalDateTime.of(localDate, localTime);
	}

	private EasyDate(LocalDateTime localDateTime) {
		this(localDateTime.toLocalDate(),localDateTime.toLocalTime());
	}

	public int getYear() {
		return this.localDateTime.getYear();
	}

	public int getMonth() {
		return this.localDateTime.getMonthValue();
	}

	public int getDay() {
		return this.localDateTime.getDayOfMonth();
	}

	public int getHour() {
		return this.localDateTime.getHour();
	}

	public int getMinute() {
		return this.localDateTime.getMinute();
	}

	public int getSecond() {
		return this.localDateTime.getSecond();
	}
	
	public boolean isAfter(EasyDate easyDate) {
		return this.localDateTime.isAfter(easyDate.localDateTime);
	}
	
	public boolean isBefore(EasyDate easyDate) {
		return this.localDateTime.isBefore(easyDate.localDateTime);
	}

	public EasyDate plusYears(int years) {
		return new EasyDate(this.localDateTime.minusYears(years));
	}
	
	public EasyDate minusYears(int years) {
		return new EasyDate(this.localDateTime.minusYears(years));
	}
	
	public String toStringTimeStamp() {
		return "" + this.getYear() + this.getMonth() + this.getDay() 
					+ this.getHour() + this.getMinute()+ this.getSecond();		
	}

	@Override
	public EasyDate clone(){
		return new EasyDate(this.localDateTime);
	}
	
	@Override
	public String toString() {
		//Formato ISO 8601
		return String.format("%4d-%02d-%02dT%02d:%02d:%02d", 
				this.getYear(), this.getMonth(), this.getDay(), 
				this.getDay(), this.getMinute(), this.getSecond());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.localDateTime == null) ? 0 : this.localDateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		EasyDate other = (EasyDate) object;
		if (this.localDateTime == null) {
			if (other.localDateTime != null)
				return false;
		} else if (!this.localDateTime.equals(other.localDateTime))
			return false;
		return true;
	}
	
	
} 
