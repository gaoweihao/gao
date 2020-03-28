package vo;

public class UnitVo {
	private Long id;
	private String name;
	private Long pid;

	@Override
	public String toString() {
		return "UnitVo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pid=" + pid +
				", comment='" + comment + '\'' +
				'}';
	}

	private String comment;

	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {

		return comment;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
