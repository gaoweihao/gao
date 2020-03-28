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
 * 修改 http://65res.gbxxzyzx.com  下的html文件
 *
 * 根据资源库提供的路径读取html 文件，
 * 将html 文件中的绝对路径修改为相对路径
 * */
public class DhUpdateAndDownHtml {
	public static void main(String[] args) {
		try {
			updateRes();
		} catch (Exception e1) {
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
                "where r.ORIGIN='0' and c.status=1 and r.TYPE=7 order by r.id asc";//and r.id >4586
		List<ResourceInfo> infoList=runner.query(sql, new BeanListHandler<ResourceInfo>(ResourceInfo.class));
		System.out.println("查询出数据总条数："+infoList.size());
		
		replace1(infoList);
	}
	
	public static void replace1(List<ResourceInfo> infoList ) throws Exception{
		int j =1 ;
		for(ResourceInfo info:infoList){
			StringBuffer html=new StringBuffer();
			URL url = new URL("http://dhxxres.gbxxzyzx.com"+info.getResourceUrl());
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
		    BufferedReader br = new BufferedReader(isr);//为字符输入流添加缓冲
		    //循环读取数据
		    String data = null;
		    while ((data = br.readLine()) != null)
				FileUtil.readDate(html,data,getReplayMap());//替换字符串

		    if(!html.toString().contains("</html>"))
		    	html.append("</html>");
		    String path="E:\\tmp_epub"+info.getResourceUrl().substring(0,info.getResourceUrl().lastIndexOf("/"));
		    FileUtil.createFile(path);//创建文件夹
			//创建html文件
			FileUtil.WriterHtml(path,html,info);
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
		map.put("http://resource.gbxx123.com/book/epubs/","../../../");
		map.put("http://dhxxres.gbxxzyzx.com,","../../../../../..");
		return  map;
	}

	/**
	 *循环读取数据修改相应的地址
	 * @param html 新创建的html
	 * @param data  一行一行读取的html文件内容
	 */
	/*public static void readDate(StringBuffer html,String data){
		if(null!=data && data.contains("http://resource.gbxx123.com/book/epubs/")){
      	  data = data.replaceAll("http://resource.gbxx123.com/book/epubs/", "../../../");
        }
        if(null!=data && data.contains("http://dhxxres.gbxxzyzx.com")){
      	  data = data.replaceAll("http://dhxxres.gbxxzyzx.com", "../../../../../..");
        }
        if(null!=data){
      	  html.append(data+"\n");
      	}
	}*/
}
