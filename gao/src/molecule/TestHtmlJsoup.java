package molecule;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import util.JdbcUtil;
import bean.NodesMoleculeBean;

public class TestHtmlJsoup {

	public static void main(String[] args) {
		QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
		String sql = "SELECT id,lineData,topology,pos,summary FROM nodes_moleculars where id =?";
		Object[] params={1};
		NodesMoleculeBean node;
		try {
			node =  runner.query(sql,new BeanHandler<NodesMoleculeBean>(NodesMoleculeBean.class),params);
			//System.out.println(node.getSummary());
			parseHtml(node.getSummary());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void parseHtml(String html){
		Document document = Jsoup.parse(html);
		Elements links = document.getElementsByTag("p");
		for (Element link : links) {
			if(link.toString().contains("img")){
				Document imgDoc =Jsoup.parse(link.toString());
				Elements imgEle = imgDoc.getElementsByTag("img");
				System.out.println(imgEle.get(0).attr("src"));
				System.out.println(imgEle.get(0).attr("title"));
				Elements spanEle = imgDoc.getElementsByTag("span");
				System.out.println(spanEle.text());	
			}
			//System.out.println("1+    "+link.toString());
		}
		
		
	
	}
	
	
	
}
