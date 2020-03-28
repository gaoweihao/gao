package bean;

public class Lwpraise {
	private Long id;
	private Long UserId;
	private Integer version;
	private Long talkid;
	private Integer times;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return UserId;
	}
	public void setUserId(Long userId) {
		UserId = userId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Long getTalkid() {
		return talkid;
	}
	public void setTalkid(Long talkid) {
		this.talkid = talkid;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
}
