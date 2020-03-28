package bean;

public class ResourcePraise {
	private Long id;
	private Long resourceId;
	private Integer up;
	private Integer version;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getUp() {
		return up;
	}
	public void setUp(Integer up) {
		this.up = up;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
