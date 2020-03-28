package bean;

public class UserBaseInfo {

	private long id;
	private String userName;
	private String account;
	private String mobile;
	public UserBaseInfo(){
	}
	
	public UserBaseInfo(long id,String userName){
		this.id=id;
		this.userName=userName;
	}
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

	@Override
	public String toString() {
		return "UserBaseInfo [id:" + id + ", userName():"
				+ userName + "]";
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
}
