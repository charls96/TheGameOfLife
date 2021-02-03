import java.time.LocalDate;
import java.time.LocalTime;

public class Session {
	
	public static final String[] stateSession = {"ACTIVE", "CLOSED"};
	
	private User user;
	private String date;
	private String startTime;
	private String endTime;
	private String state;
	
	
	public Session(User user) {
		assert user != null;
		
		this.setUser(user);
		this.date = LocalDate.now().toString();
		this.startTime = LocalTime.now().toString();
		this.endTime = new String();
		this.state = stateSession[0];
	}

	public Session() {
		this.user = new User();
		this.date = new String();
		this.startTime = new String();
		this.endTime = new String();
		this.state = stateSession[0];
	}
	
	public Session(Session session) {
		this.user = new User(session.user);
		this.date = new String(session.date);
		this.startTime = new String(session.startTime);
		this.endTime = new String(session.endTime);
		this.state = new String(session.state);
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
	
	@Override
	public Session clone() {
		return  new Session(this);
	}
		
	@Override
	public String toString() {
		return String.format(
				"%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s\n",
						"user:", user.getName(), 
						"date:", date, 
						"startTime:", startTime, 
						"endTime:", endTime, 
						"state:", state	
				);
	}
}
