package html;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.FileUtil;
import util.JdbcUtil;
import bean.ResourceInfo;
/**
 * http://65res.gbxxzyzx.com  下的html文件
 * 根据资源库提供的路径读取html 文件，并修改html文件中的内容
 * 同时在本地生成html文件
 * */
public class JyUpdateAndDownHtml {
	public static void main(String[] args) {
		try {
			//修改
			updateRes();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}

	/**
	 * 修改 http://65res.gbxxzyzx.com 中存储的html文件
	 * */
	public static void updateRes()throws Exception{
		QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select r.id, r.title,r.type,r.resourceurl ,r.TITLEPIC from T_COLUMNRESOURCE  c "+
                "left join T_RESOURCEINFO r ON c.RESOURCEID=r.ID "+
                "where c.COLUMNID not in(220,221,222) and c.status=? and r.TYPE=7 and r.id<>7499 order by r.id asc";//and r.id >4586
		sql="select r.id, r.title,r.type,r.resourceurl ,r.TITLEPIC from T_RESOURCEINFO r " +
				" INNER JOIN T_PAGECONTENTINFO p ON p.RESOURCEID=r.ID "+
				"where p.contentinfo like '%http://65mgr.gbxxzyzx.com%'";
		Object[] params={};
		List<ResourceInfo> infoList=runner.query(sql, new BeanListHandler<ResourceInfo>(ResourceInfo.class),params);
		System.out.println("查询出数据总条数："+infoList.size());

		//replace1(infoList);
	}
	
	public static void replace1(List<ResourceInfo> infoList ) throws Exception{
		int j =1 ;
		for(ResourceInfo info:infoList){
			StringBuffer html=new StringBuffer();
			URL url = new URL("http://65res.gbxxzyzx.com"+info.getResourceUrl());
			System.out.println(url.toString());
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
		    BufferedReader br = new BufferedReader(isr);//为字符输入流添加缓冲
		    
		    //循环读取数据
		    String data = null;
		    while ((data = br.readLine()) != null)
				FileUtil.readDate(html,data,getReplayMap());//替换字符串

		    if(!html.toString().contains("</html>")) html.append("</html>");
		    String path="E:\\tmp_epub4"+info.getResourceUrl().substring(0,info.getResourceUrl().lastIndexOf("/"));
			FileUtil.createFile(path);//创建文件夹
			FileUtil.WriterHtml(path,html,info);//创建html文件
		    isr.close();
			is.close();
			System.out.println(j + "   "+info.getId());
			j++;
		}
	}
	/**
	 * 获取替换的Map<String,String>
	 * */
	public static Map<String,String> getReplayMap(){
		Map<String,String> map= Maps.newHashMap();
		map.put("http://65mgr.gbxxzyzx.comhttp://65res.gbxxzyzx.com/","/upload/");
		//map.put("http://65res.gbxxzyzx.com","http://mjres.xjzx.com");
		//map.put("http://resource.gbxx123.com/book/epubs/", "../../../");
		return  map;
	}

}
