package jmh.jackson;

import java.util.List;
import java.util.Objects;

/**
 * @author erabii
 *
 */
public class User {

	public String id;

	public int age;

	public double latitude;

	public double longitude;

	public long paycheck;

	public List<Friend> friends;

	public User() {

	}

	public User(String id, int age, double latitude, double longitude, long paycheck, List<Friend> friends) {
		super();
		this.id = id;
		this.age = age;
		this.latitude = latitude;
		this.longitude = longitude;
		this.paycheck = paycheck;
		this.friends = friends;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}
		User user = (User) o;
		return age == user.age && id == user.id && paycheck == user.paycheck
				&& Double.compare(user.latitude, latitude) < 1e-13 && Double.compare(user.longitude, longitude) < 1e-13
				&& Objects.equals(friends, user.friends);
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, id, paycheck, latitude, longitude, friends);
	}

	public String getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public long getPaycheck() {
		return paycheck;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setPaycheck(long paycheck) {
		this.paycheck = paycheck;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

}
