package bean;

import java.util.List;

public class JsonVo {
	private Integer num;//红包总个数
	private String type;//设置红包类型
	private String startDate;//红包开始时间
	List<RedJsonVo> redJsonList;
	public List<RedJsonVo> getRedJsonList() {
		return redJsonList;
	}
	public void setRedJsonList(List<RedJsonVo> redJsonList) {
		this.redJsonList = redJsonList;
	}
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
