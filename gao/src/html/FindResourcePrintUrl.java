package html;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.JdbcUtil;
import bean.ResourceInfo;
/**
 * 获取数据库中的地址，
 * 根据地址得到相应的html 文件，
 * 把文件中的视频的路径打印到控制台
 * */
public class FindResourcePrintUrl {
	public static void main(String[] args) {
		 BufferedReader br =null;
		 InputStream is=null;
		 InputStreamReader isr=null;
		try {
			QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
			//String sql = "SELECT ID,TITLE,RESOURCEURL FROM T_RESOURCEINFO WHERE TYPE=? order by id desc limit 0,40";
			String sql ="SELECT r.ID,r.TITLE,r.RESOURCEURL FROM T_RESOURCEINFO  r "
					  + "INNER JOIN T_COLUMNRESOURCE  cr ON cr.RESOURCEID=r.ID "
			          + "WHERE cr.STATUS = 1 AND r.TYPE=? order by r.CREATETIME DESC ";//LIMIT 1500,500
			Object[] params={11};
			
			List<ResourceInfo> infoList=runner.query(sql, new BeanListHandler<ResourceInfo>(ResourceInfo.class),params);
			System.out.println(infoList.size());
			for(ResourceInfo info:infoList){
				URL url = new URL("http://resource.gbxx123.com"+info.getResourceUrl());
				is = url.openStream();
	            isr = new InputStreamReader(is,"utf-8");
	            //为字符输入流添加缓冲
	            br = new BufferedReader(isr);
	            String data = br.readLine();//读取数据
	           while (data!=null){//循环读取数据
	                  data = br.readLine();
	                  //System.out.println(data);
	                  if(data.contains("<video poster=")){
	                	  System.out.println("http://10.1.2.42"+data.substring(data.indexOf("src=\"") + 33,data.indexOf("\" controls=\"controls\"")));
	                	  break;
	                  }
	             }
			}
            br.close();
            isr.close();
            is.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
	    
	}

}
