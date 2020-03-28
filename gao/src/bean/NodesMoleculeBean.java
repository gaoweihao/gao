package bean;

import java.util.Date;

public class NodesMoleculeBean {
	private Integer id;
	
	/**分子式id*/
	private Integer molecular_id;
	
	/**节点的uuid*/
	private String uuid;
	
	/**父节点默认0表示父节点*/
	private String pid;
	
	/**节点层级默认0,表述顶级*/
	private Integer level;
	
	/**节点名称*/
	private String nodeName;
	
	/**节点概述*/
	private String outlines;
	
	/**节点概述*/
	private String summary;
	
	/**节点筛选关键词*/
	private String keywords;
	
	/**节点是否为重要知识点,默认不是重要,1表示重要*/
	private Integer is_focus;
	
	/**在u3d连线的数据的json数据,其中包含{l:"",r:"",s:""}*/
	private String lineData;
	
	/**在u3d中的位置颜色信息*/
	private String pointColor;
	
	/**在u3d中的标题颜色*/
	private String titleColor;
	
	/**在u3d中的坐标信息*/
	private String pos;
	
	/**拓扑图loc信息*/
	private String loc;
	
	/**拓扑图位置点信息*/
	private String topology;
	
	/**拓扑图节点id|*/
	private Integer topology_id;
	
	/**url*/
	private String url;
	
	/**状态默认1表示启用,-1表示删除状态*/
	private Integer node_status;
	
	/**创建时间*/
	private Date created_at;
	
	/**更新时间*/
	private Date updated_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMolecular_id() {
		return molecular_id;
	}

	public void setMolecular_id(Integer molecular_id) {
		this.molecular_id = molecular_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getOutlines() {
		return outlines;
	}

	public void setOutlines(String outlines) {
		this.outlines = outlines;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getIs_focus() {
		return is_focus;
	}

	public void setIs_focus(Integer is_focus) {
		this.is_focus = is_focus;
	}

	public String getLineData() {
		return lineData;
	}

	public void setLineData(String lineData) {
		this.lineData = lineData;
	}

	public String getPointColor() {
		return pointColor;
	}

	public void setPointColor(String pointColor) {
		this.pointColor = pointColor;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getTopology() {
		return topology;
	}

	public void setTopology(String topology) {
		this.topology = topology;
	}

	public Integer getTopology_id() {
		return topology_id;
	}

	public void setTopology_id(Integer topology_id) {
		this.topology_id = topology_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getNode_status() {
		return node_status;
	}

	public void setNode_status(Integer node_status) {
		this.node_status = node_status;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
}
