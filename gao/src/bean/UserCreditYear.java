package bean;

import javax.xml.crypto.Data;

public class UserCreditYear {
	private long id;
	private String userName;
	private double credit;
	private long userId;
	private Data year;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Data getYear() {
		return year;
	}
	public void setYear(Data year) {
		this.year = year;
	}
	
	
	

}
