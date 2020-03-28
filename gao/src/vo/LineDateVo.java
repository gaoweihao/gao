package vo;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class LineDateVo {

	/*{"text":"\u65b0\u8282\u70b9",
	"nodeId":"15095212720020034626429669374036070060",
	"parentId":"15095212720020034626429669409426092092",
	"level":1,
	"keywords":"",
	"is_focus":"",
	"summary":"",
	"titleColor":{"a":1,"r":0.360784322,"g":0.447058827,"b":0.4392157},
	"lineData":{"s":0,"r":0,"l":1},
	"pointColor":{"a":1,"r":0.8980392,"g":0.6156863,"b":0.5372549},
	"pos":{"x":1.20733428,"y":2.25,"z":1.57475209},
	"url":null,
	"loc":"120.42472205499931 0",
	"id":-2
	}*/
	private String text;
	private String nodeId;
	private String parentId;
	private Integer level;
	private String keywords;
	private String is_focus;
	private String summary;
	private String titleColor;
	/*private String lineData;
	private String pointColor;
	private String  pos;*/
	/*private ColorEntity pointColor;
	private ColorEntity titleColor;
	private JSONObject lineData;
	private JSONObject pos;*/
	private String url;
	private String loc;
	private Integer id ;
	private float r;//球与球之间的相关度
	private float s;//球与球之间的相似度
	private Integer l;//球的等级
	public float getR() {
		return r;
	}
	public void setR(float r) {
		this.r = r;
	}
	public float getS() {
		return s;
	}
	public void setS(float s) {
		this.s = s;
	}
	public Integer getL() {
		return l;
	}
	public void setL(Integer l) {
		this.l = l;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getIs_focus() {
		return is_focus;
	}
	public void setIs_focus(String is_focus) {
		this.is_focus = is_focus;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/*public ColorEntity getPointColor() {
		return pointColor;
	}
	public void setPointColor(ColorEntity pointColor) {
		this.pointColor = pointColor;
	}*/
	/*public ColorEntity getTitleColor() {
		return titleColor;
	}
	public void setTitleColor(ColorEntity titleColor) {
		this.titleColor = titleColor;
	}
	public JSONObject getLineData() {
		return lineData;
	}
	public void setLineData(JSONObject lineData) {
		this.lineData = lineData;
	}
	public JSONObject getPos() {
		return pos;
	}
	public void setPos(JSONObject pos) {
		this.pos = pos;
	}*/
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitleColor() {
		return titleColor;
	}
	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}
	
	
}
