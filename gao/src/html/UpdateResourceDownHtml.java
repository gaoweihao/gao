package html;

import bean.ResourceInfo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.FileUtil;
import util.JdbcUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
/**
 * 根据资源库提供的路径读取html 文件，并修改html文件中的内容
 * 同时在本地生成html文件
 * */
public class UpdateResourceDownHtml {
	public static  String urls="";
	public static void main(String[] args) {
		try {
			//修改http://resource.gbxx123.com 下的html 文件
			updateResource();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
	    
	}
	/**
	 * 修改http://resource.gbxx123.com 下的html 文件
	 * */
	public static void updateResource()throws Exception{
		QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
		String sql ="SELECT r.ID,r.TITLE,r.RESOURCEURL FROM T_RESOURCEINFO  r "
				  + "INNER JOIN T_COLUMNRESOURCE  cr ON cr.RESOURCEID=r.ID "
		          + "WHERE cr.STATUS = 1 AND cr.COLUMNID in(220,221,222) AND r.TYPE IN(7,11) AND r.ID NOT IN(4586,2681,2407) order by r.CREATETIME DESC ";//LIMIT 0,1 AND r.TYPE=?
		Object[] params={};
		List<ResourceInfo> infoList=runner.query(sql, new BeanListHandler<ResourceInfo>(ResourceInfo.class),params);
		//System.out.println("查询出数据总条数："+infoList.size());
		for(ResourceInfo info:infoList){
			//System.out.println(info.getId());
			replace(info);
		}
	}

	public static void replace(ResourceInfo info ) throws Exception{
		String src="";
		URL url = new URL("http://resource.gbxx123.com"+info.getResourceUrl());
		urls=url.toString();
		//System.out.println("http://10.1.3.40"+info.getResourceUrl().substring(0, info.getResourceUrl().lastIndexOf("/")));
	//	System.out.println(url.toString());
		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is,"utf-8");
		BufferedReader br = new BufferedReader(isr);//为字符输入流添加缓冲
		
        StringBuffer html= new StringBuffer();
        String data = "";
        while ((data = br.readLine()) != null){//循环读取数据
			String str=updateResourceURl(info.getResourceUrl().substring(info.getResourceUrl().lastIndexOf("/epubs/")+7, info.getResourceUrl().lastIndexOf("/chapter1.html")));
        	readDate1(data,str,src,html);
         }
        //创建文件夹
        String path="E:/tmp_epub3"+info.getResourceUrl().substring(0,info.getResourceUrl().lastIndexOf("/"));
        FileUtil.createFile(path);
		
		//创建html文件
        FileUtil.WriterHtml(path, html, info);
        isr.close();
		is.close();
	}
	/**
	 * 用../替换资源URL中的/2016/9/...
	 * */
	public static String updateResourceURl(String resourceUrl){
		int number =resourceUrl.split("/").length;
		StringBuffer str=new StringBuffer("");
		for(int i=0;i<number;i++){
			str.append("../");
		}
		return str.toString();
	}
	//循环读取html文件内容
	public static void readDate1(String data,String str,String src,StringBuffer html){
		 //修改js文件
        if(null!=data&&data.contains("<script type=\"text/javascript")){
      	  src=data.substring(data.indexOf("src=\"") +44,data.indexOf("\"></script>"));
      	  data=data.substring(0,data.indexOf("src=\"") + 5)+str+src+data.substring(data.indexOf("\"></script>"),data.length());
        }
        //修改css文件路径
        if(null!=data&&data.contains("<link href=")){
      	  src=data.substring(data.indexOf("href=\"") +45,data.indexOf("\" rel=\"stylesheet\""));
      	  //System.out.println(src+"###");
      	  data=data.substring(0,data.indexOf("href=\"") + 6)+str+src+data.substring(data.indexOf("\" rel=\"stylesheet\""),data.length());
        }
        //修改poster=
        if(null!=data&&data.contains("<video poster=")){
      	  src=data.substring(data.indexOf("poster=\"") +37,data.indexOf("\" width=\"80%\""));
      	  //System.out.println(src+"###");
      	  data=data.substring(0,data.indexOf("poster=\"") + 8)+str+src+data.substring(data.indexOf("\" width=\"80%\""),data.length());
        }
        //修改 mp4
        if(null!=data&&data.contains("<video poster=")){
      	  src=data.substring(data.indexOf("src=\"") +71,data.indexOf("\" controls=\"controls\""));
      	  //System.out.println(src+"###");
      	  data=data.substring(0,data.indexOf("src=\"") + 5)+str+"video/mp4/"+src+data.substring(data.indexOf("\" controls=\"controls\""),data.length());
        }
		//修改 image
		if(null!=data&&data.contains("<img src=")){
			if(data.contains(".jpg\"")){
				src=data.substring(data.indexOf("<img src=\"")+10,data.indexOf(".jpg\""));
				//System.out.println(src+"###");
				data=data.substring(0,data.indexOf("<img src=\"")+10)+str+"image/"+src+data.substring(data.indexOf(".jpg\""),data.length());
			}
			if(data.contains(".png\"")){
				System.out.println(urls);
				src=data.substring(data.indexOf("<img src=\"")+10,data.indexOf(".png\""));
			//	System.out.println(src+"###");
				data=data.substring(0,data.indexOf("<img src=\"")+10)+str+"image/"+src+ data.substring(data.indexOf(".png\""),data.length());
			}
			//System.out.println(data);
		}
      html.append(data);
	}
}