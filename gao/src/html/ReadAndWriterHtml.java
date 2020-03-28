package html;

import com.google.common.collect.Lists;
import util.FileUtil;

import java.io.*;
import java.util.List;

/**
 * 更新问卷、考试html页中js文件路径
 * 变更为相对路径
 */
public class ReadAndWriterHtml {
	public static void main(String[] args) {
		try {
			//获取指定文件夹下的文件
			//List<String> htmlList= FileUtil.readfile("E:\\exam");
			List<String> htmlList= FileUtil.readfile("E:\\wj");
			//循环遍历文件
			for(String htmlPath:htmlList){
				File file = new File(htmlPath);
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"utf-8");
				BufferedReader br = new BufferedReader(isr);//为字符输入流添加缓冲
				String str="../jquerymobile/";
		        StringBuffer html= new StringBuffer();
		        String data = "";
		        while ((data = br.readLine()) != null){//循环读取数据
		        	readDate1(data,str,html);
		         }
		        //替换原有html文件
		        WriterHtml(htmlPath, html.toString());
		        isr.close();
			}
			System.out.println("success");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//循环读取替换html文件内容
	public static void readDate1(String data,String str,StringBuffer html){
		 //修改css文件
        if(null!=data&&data.contains("//apps.bdimg.com/libs/jquerymobile/1.4.5")){
      	  data=data.replace("//apps.bdimg.com/libs/jquerymobile/1.4.5/", str);
        }
        //修改jquery文件路径
        if(null!=data&&data.contains("//apps.bdimg.com/libs/jquery/1.10.2")){
        	data=data.replace("//apps.bdimg.com/libs/jquery/1.10.2/", str);
      	}
      html.append(data+"\n");
	}
	
	//创建文件夹并写出相应的html
	public static void WriterHtml(String htmlPath,String html) throws Exception{
		File htmlFile=new File(htmlPath);
	    htmlFile.createNewFile();
	    FileOutputStream  htmlOut=new FileOutputStream(htmlFile);
	    BufferedWriter	buffereWriterHtml=new BufferedWriter(new PrintWriter(new OutputStreamWriter(htmlOut, "UTF-8")));
		buffereWriterHtml.write(html.toString());
		buffereWriterHtml.close();
		htmlOut.close();
	}
}
