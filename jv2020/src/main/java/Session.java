import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Session {
	private User user;
	private String date;
	private String startTime;
	private String endTime;
	private String state;
	
	
	
	public Session(User user) {

		this.setUser(user);
		this.date = LocalDate.now().toString();
		this.startTime = new Date().toString();;
		this.endTime = "";
	}

	public User getUser() {
		return user;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public String getState() {
		return state;
	}
	
	public void setUser(User user) {
		assert user != null;
		this.user = user;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}
