package html;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import com.google.common.collect.Lists;
import net.sf.json.JSONObject;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.FileUtil;
import util.JdbcUtil;
import bean.ResourceInfo;
/**
 * 下载资源库中资源图片
 * 根据数据库中提供的json 串，
 * 获取key值为first的字符串拼接url地址，
 * 并且按照所获得的字符串保存其相应的路径
 * 对应的图片下载到其相对的路径下 
 * */
public class FindImgUrlDownImage {
	public static void main(String[] args) {
		try {
			QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
			String sql ="select r.id, r.title,r.type,r.resourceurl ,r.TITLEPIC from T_COLUMNRESOURCE  c "+
	                    "left join T_RESOURCEINFO r ON c.RESOURCEID=r.ID "+
	                    "where c.COLUMNID in(220,221,222) and c.status=? and r.id>2681 order by r.id asc";//and r.id >4586 and
			/**
			 * 2407/2681 --只有一个first，没有图片
			 * */
			Object[] params={1};
			List<ResourceInfo> infoList=runner.query(sql, new BeanListHandler<ResourceInfo>(ResourceInfo.class),params);
			System.out.println("查询出数据总条数："+infoList.size());
			
			//循环下载图片
			downloadPicture( infoList);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
	/**
	 * 下载图片到本地
	 * {"first":"/site/image/courseware/1418972555452/1418972555452.jpg",
	 * "second":"/site/image/courseware/1418972558110/1418972558110.jpg",
	 * "third":"/site/image/courseware/1418972562512/1418972562512.jpg"}
	 * */
	 @SuppressWarnings("unused")
	private static void downloadPicture(List<ResourceInfo> infoList) {
		 try {    
			 for (ResourceInfo info:infoList) {
				 System.out.println(info.getId());
				 List<String> list=analyzeJsonList(info.getTitlePic());
				 if(null!=list&&list.size()>0){
				 	for(String first : list){
						//创建文件夹
						String path = "E:/aaaa" + first.substring(0, first.lastIndexOf("/"));
						System.out.println("文件夹：" + path);
						FileUtil.createFile(path);

						//拼接图片地址
						URL url = new URL("http://10.1.2.42" + first);
						System.out.println("url 路径:" + url.toString());

						//下载图片
						DataInputStream dataInputStream = new DataInputStream(url.openStream());
						if (null == dataInputStream) continue;//判断图片不存在，跳出本次循环
						FileOutputStream fileOutputStream = new FileOutputStream(new File("E:/aaaa" + first));

						byte[] buffer = new byte[1024];
						int length;
						while ((length = dataInputStream.read(buffer)) > 0) {
							fileOutputStream.write(buffer, 0, length);
						}
						dataInputStream.close();
						fileOutputStream.close();
					}
				 }
			 }
            } catch (MalformedURLException e) {  
                e.printStackTrace(); 
            } catch (IOException e) {  
                e.printStackTrace();
            }
	    }

	/**
	 * * {"first":"/site/image/courseware/1418972555452/1418972555452.jpg",
	 * "second":"/site/image/courseware/1418972558110/1418972558110.jpg",
	 * "third":"/site/image/courseware/1418972562512/1418972562512.jpg"}
	 * */
	public static List<String> analyzeJsonList(String jsonStr){
		List<String> list= Lists.newArrayList();
		JSONObject json = JSONObject.fromObject(jsonStr);
		if(null!=json&&!json.isEmpty()){
			list.add(json.getString("first"));
			list.add(json.getString("second"));
			list.add(json.getString("third"));
		}
		return list;
	}
}
