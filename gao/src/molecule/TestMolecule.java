package molecule;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import util.JdbcUtil;
import vo.MocularsJsonVo;
import bean.Moleculars;

public class TestMolecule {

	public static void main(String[] args) {
		QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
		String sql = "SELECT * FROM `moleculars` where id =?" ;/*"SELECT "
				+ "id,user_id,uuid,molecular_name,author_name,molecular_desc,"
				+ "latitude_id,grid_id,molecular_status,nodes_u3d_info, "
				+ "nodes_info,online_time,created_at "
				+ "FROM `moleculars`where id =?";*/
		Object[] params={1};
		Moleculars molecule;
		try {
			molecule = runner.query(sql,new BeanHandler<Moleculars>(Moleculars.class),params);
			if(molecule!=null){
				System.out.println(molecule.getId());
				/*String nodeInfo=molecule.getNodes_info().replace("\"class\":", "\"top\":");
				//System.out.println(nodeInfo);
				MocularsJsonVo vo=JSON.parseObject(nodeInfo,MocularsJsonVo.class);
				String linkDataArray =nodeInfo.substring(nodeInfo.indexOf("\"linkDataArray\":"));
				//System.out.println(linkDataArray);
				//System.out.println("#####"+vo.getLinkDataArray());
				JSONObject json = new JSONObject();
				json.put("class", vo.getTop());
				json.put("nodeKeyProperty", vo.getNodeKeyProperty());
				json.put("linkDataArray", vo.getLinkDataArray());
				System.out.println(json.toJSONString());*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			

	}

}
