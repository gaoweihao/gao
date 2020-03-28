package vo;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class MocularsJsonVo {
	//node_info 中的数据
	private String top;
	private String nodeKeyProperty;
	private String nodeDataArray;
	private List<JSONObject> linkDataArray;
	
	
	
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getNodeKeyProperty() {
		return nodeKeyProperty;
	}
	public void setNodeKeyProperty(String nodeKeyProperty) {
		this.nodeKeyProperty = nodeKeyProperty;
	}
	public String getNodeDataArray() {
		return nodeDataArray;
	}
	public void setNodeDataArray(String nodeDataArray) {
		this.nodeDataArray = nodeDataArray;
	}
	public List<JSONObject> getLinkDataArray() {
		return linkDataArray;
	}
	public void setLinkDataArray(List<JSONObject> linkDataArray) {
		this.linkDataArray = linkDataArray;
	}
	
	
	
	
	//
	

}
