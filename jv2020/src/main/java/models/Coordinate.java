package models;

import java.io.Serializable;

public class Coordinate implements Serializable {
	private int x;
	private int y;
	
	public Coordinate() {
		this(0, 0);
	}
	
	public Coordinate(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public Coordinate(Coordinate coordinate) {
		assert coordinate != null;
		this.x = coordinate.x;
		this.y = coordinate.y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		if (x >= 0) {
			this.x = x;
		}
	}
	
	public void setY(int y) {
		if (y >= 0) {
			this.y = y;
		}
	}

	public int compareTo(Coordinate anotherCoordinate) {
		if (this.equals(anotherCoordinate)) {
			return 0;
		}
		if (this.x < anotherCoordinate.x || this.y < anotherCoordinate.y) {
			return -1;
		}
		return 1;	
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public String toString() {
		return String.format("(%s, %s)", x, y);
	}

	@Override
	public Coordinate clone() {
		return new Coordinate(this);
	}
}
