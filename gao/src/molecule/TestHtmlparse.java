package molecule;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import util.JdbcUtil;
import bean.NodesMoleculeBean;

public class TestHtmlparse {
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
		 try { 
			Parser parser = new Parser(html);
			NodeIterator iterator = parser.elements ();
			 while(iterator.hasMoreNodes()){
                Node node = iterator.nextNode();  
                //System.out.println("getText:"+node.getText());  
                System.out.println("getPlainText:"+node.toPlainTextString());
                NodeList nodeList=node.getChildren();
                for(int j=0;j<nodeList.size();j++){
                	Node no=nodeList.elementAt(j);
                	if(no.toHtml().contains("img")){
                		ImageTag  img=(ImageTag)no;
                		String path=img.getAttribute("src");
                		System.out.println(path);
                		System.out.println(img.getAttribute("title"));
                	}
                	if(no.toHtml().contains("span")){
                		Span  span=(Span)no;
                	}
                	
                }
            }
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
}
