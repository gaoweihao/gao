package bean;

import java.util.Date;

/**
 * 
 * 知识分子式结构临时表
 * 
 * */
public class Moleculars {
	
	private Integer id;
	private Integer user_id;
	private String uuid;
	private String molecular_name;
	private String author_name;
	private String molecular_desc;
	private Integer latitude_id;
	private Integer grid_id;
	private Integer molecular_status;
	private String nodes_u3d_info;
	private String nodes_info;
	private String online_time;
	private Date created_at;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getMolecular_name() {
		return molecular_name;
	}
	public void setMolecular_name(String molecular_name) {
		this.molecular_name = molecular_name;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	
	public Integer getLatitude_id() {
		return latitude_id;
	}
	public void setLatitude_id(Integer latitude_id) {
		this.latitude_id = latitude_id;
	}
	public Integer getGrid_id() {
		return grid_id;
	}
	public void setGrid_id(Integer grid_id) {
		this.grid_id = grid_id;
	}
	public Integer getMolecular_status() {
		return molecular_status;
	}
	public void setMolecular_status(Integer molecular_status) {
		this.molecular_status = molecular_status;
	}
	public String getNodes_u3d_info() {
		return nodes_u3d_info;
	}
	public void setNodes_u3d_info(String nodes_u3d_info) {
		this.nodes_u3d_info = nodes_u3d_info;
	}
	public String getNodes_info() {
		return nodes_info;
	}
	public void setNodes_info(String nodes_info) {
		this.nodes_info = nodes_info;
	}
	public String getOnline_time() {
		return online_time;
	}
	public void setOnline_time(String online_time) {
		this.online_time = online_time;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public String getMolecular_desc() {
		return molecular_desc;
	}
	public void setMolecular_desc(String molecular_desc) {
		this.molecular_desc = molecular_desc;
	}
	
}
