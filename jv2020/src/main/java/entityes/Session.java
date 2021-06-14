package entityes;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Session other = (Session) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (state != other.state)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
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
