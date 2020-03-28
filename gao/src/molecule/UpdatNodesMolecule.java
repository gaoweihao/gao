package molecule;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.JdbcUtil;
import vo.LineDateVo;
import bean.NodesMoleculeBean;

import com.alibaba.fastjson.JSON;

public class UpdatNodesMolecule {
	
	public static void main(String[] args) {
		QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
		String sql = "SELECT id,lineData,topology,pos FROM nodes_moleculars where molecular_id =?";
		Object[] params={1};
		List<NodesMoleculeBean> nodeList;
		try {
			
			nodeList =  runner.query(sql,new BeanListHandler<NodesMoleculeBean>(NodesMoleculeBean.class),params);
			if(null!=nodeList&&nodeList.size()>0){
				for(NodesMoleculeBean entity: nodeList){
					/*System.out.println(entity.getLineData());
					LineDateVo linedate = JSON.parseObject(entity.getLineData(),LineDateVo.class);
					System.out.println(linedate.getR());*/
					String s=entity.getPos().replace("\\\"", "\"");
					System.out.println( s.substring(1,s.length()-1));
					
					System.out.println(entity.getId());
					//LineDate(entity);
				
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static String LineDate(NodesMoleculeBean node){
		String s = "{\"id\":0,\"parentId\":\"0\",\"loc\":\"0 89.9827799864635\",\"text\":\"\u6d4b\u8bd51\",\"nodeId\":\"15095212720020034626429669409426092092\",\"level\":\"0\",\"keywords\":\"\u6d4b\u8bd5,1\",\"is_focus\":\"1\",\"summary\":\"<p><img src=\"/uploads/images/1506481446742556.jpg\" title=\"1506481446742556.jpg\" alt=\"1506481446742556.jpg\" height=\"240\" width=\"375\"/><span style=\"font-size: 12px; line-height: 20px; color: #999999; display:block; width:375px; text-align: left; padding:0px 10px 0px 10px;box-sizing: border-box;\">请输入描述</span></p><h1 style=\"font-size: 16px; line-height: 30px; padding:0px 10px 0px 10px; box-sizing: border-box; text-align:left;\">目录1</h1><p style=\"font-size: 14px; line-height: 24px; padding:0px 10px 0px 10px;box-sizing: border-box;\">请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文</p><p style=\"font-size: 14px; line-height: 24px; padding:0px 10px 0px 10px;box-sizing: border-box;\">请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文请在此输入正文</p><p style=\"font-size: 14px; line-height: 24px; padding:0px 10px 0px 10px;box-sizing: border-box;\">请在此输入正文请在此输入正文请在此输入正文请在此输入正文。</p><h1 style=\"font-size: 16px; line-height: 30px; padding: 0px 10px; box-sizing: border-box; text-align: left;\">目录2<br/></h1><p style=\"font-size: 14px; line-height: 24px; padding:0px 10px 0px 10px;box-sizing: border-box;\">请在此输入正文..</p><p style=\"font-size: 14px; line-height: 24px; padding:0px 10px 0px 10px;box-sizing: border-box;\">...<br/></p>\",\"lineData\":{\"s\":0,\"r\":0,\"l\":0},\"titleColor\":{\"a\":1,\"r\":0.360784322,\"g\":0.447058827,\"b\":0.4392157},\"pointColor\":{\"a\":1,\"r\":0.8980392,\"g\":0.5294118,\"b\":0.5294118},\"pos\":{\"x\":0,\"y\":0,\"z\":0},\"url\":null}";
	
		LineDateVo vo=JSON.parseObject(node.getTopology(),LineDateVo.class);
	
		System.out.println(vo.getTitleColor());
		System.out.println(JSON.parse(vo.getTitleColor()).toString());
		
		/*//{"s":0,"r":0,"l":1}
		StringBuffer json=new StringBuffer("{\"s\":");
		json.append(RandomUtil.randomFloat()).append(",\"r\":").
		append(RandomUtil.randomFloat()).append(",\"l\":").append(vo.getL()).append("}");*/
		return "";// json.toString();
	}

}
