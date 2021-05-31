package entitys;
import java.io.Serializable;

import utils.EasyDate;

public class Session implements Identifiable, Serializable {
	
	public enum StateSession {ACTIVE, CLOSED};
	
	private User user;
	private EasyDate startTime;
	private EasyDate endTime;
	private StateSession state;
	
	public Session(User user) {
		assert user != null;
		
		this.setUser(user);
		this.startTime = EasyDate.now();
		this.endTime = EasyDate.now();
		this.state = StateSession.ACTIVE;
	}

	public Session() {
		this(new User());
	}
	
	public Session(Session session) {
		assert session != null;
		
		this.user = session.user;
		this.startTime = session.startTime.clone();
		this.endTime = session.endTime.clone();
		this.state = session.state;
	}
	
	@Override
	public String getId() {
		return this.user.getId() + "-" + this.startTime.toStringTimeStamp();
	}
	
	public User getUser() {
		return this.user;
	}
	
	public EasyDate getStartTime() {
		return this.startTime;
	}
	
	public EasyDate getEndTime() {
		return this.endTime;
	}
	
	public StateSession getState() {
		return this.state;
	}
	
	public void setUser(User user) {
		assert user != null;
		this.user = user;
	}
	
	public void setEndTime(EasyDate endTime) {
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
						+ "%15s %-15s\n",
						"user:", this.user.getName(), 
						"startTime:", this.startTime, 
						"endTime:", this.endTime, 
						"state:", this.state	
				);
	}

}
