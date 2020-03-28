package util;

import bean.ResourceInfo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mchange.v2.lang.StringUtils;

import java.io.*;
import java.util.List;
import java.util.Map;

public class FileUtil {


	/**
	 * 创建相应的文件
	 * @param path 文件夹的路劲
     */
	public static void createFile(String path){
		String pathString = "";
		String dir[];
		dir = path.replace('\\', '/').split("/");
		for (int i = 1; i < dir.length; i++) {
			File subFile = new File(pathString + "/" + dir[i]);
			if (subFile.exists() == false)
				subFile.mkdir();
			pathString += "/" + dir[i];
		}
	}

	/**
	 * 创建文件夹并写出相应的html
	 * @param path 创建文件夹的根路径
	 * @param html 写出html文件内容
	 * @param info （提供文件名）
	 * */
	public static void WriterHtml(String path,StringBuffer html,ResourceInfo info) throws Exception{
		//System.out.println(path+"/"+info.getResourceUrl().substring(info.getResourceUrl().lastIndexOf("/")+1,info.getResourceUrl().lastIndexOf("."))+".html");
		File htmlFile=new File(path+"/"+info.getResourceUrl().substring(info.getResourceUrl().lastIndexOf("/")+1,info.getResourceUrl().lastIndexOf("."))+".html");
		htmlFile.createNewFile();
		FileOutputStream  htmlOut=new FileOutputStream(htmlFile);
		BufferedWriter	buffereWriterHtml=new BufferedWriter(new PrintWriter(new OutputStreamWriter(htmlOut, "UTF-8")));
		buffereWriterHtml.write(html.toString());
		buffereWriterHtml.close();
		htmlOut.close();
	}
	 /**
	  * 递归--读取某个文件夹下的所有文件
	  * 该文件夹可以包含文件或者文件夹
	  * @param filepath 读取文件夹的绝对路径
	 */
	public static List<String> readfile(String filepath) throws IOException {
		List<String> htmlPath=Lists.newArrayList();
		File file = new File(filepath);
		if (!file.isDirectory()) {
			htmlPath.add(file.getAbsolutePath());
		} else {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "\\" + filelist[i]);
				if (!readfile.isDirectory()) {
					htmlPath.add(filepath + "\\" + filelist[i]);
				} else if (readfile.isDirectory()) {
					readfile(filepath + "\\" + filelist[i]);
				}
			}
		}
		return htmlPath;
	}

	/**
	 *循环读取数据修改相应的地址
	 * @param html 新创建的html
	 * @param data  一行一行读取的html文件内容
	 * @param map map.key -- data中被替换掉的内容，
	 *            map.value --  替换data中的内容的 String
	 */
	static  int i=0;
	public static void readDate(StringBuffer html, String data, Map<String,String> map){
		for(String key : map.keySet()){
			if(!Strings.isNullOrEmpty(data) && data.contains(key)){
				data=data.replace(key,map.get(key));
				i++;
			}
		}
		if(null!=data){
			html.append(data+"\n");
		}
		System.out.println(i);
	}
}
