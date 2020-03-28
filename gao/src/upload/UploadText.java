package upload;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;

import com.bx.mindjet.parse.EncodingDetect;
import com.bx.mindjet.parse.MindjetData;
import com.bx.mindjet.parse.NodeData;
import com.bx.mindjet.parse.ParseDataInterface;
import com.bx.mindjet.parse.TextParseDataInterface;

public class UploadText {
	private static  ParseDataInterface parseData = new TextParseDataInterface();;
	
	public static void main(String[] args) {
		uploadText();
	}
	
	private static  void uploadText(){
		try {
			File file= new File("E:\\吉林市商务局.txt");
		    MindjetData mindjetData = null;
			String fileEncode = EncodingDetect.getJavaEncode(file);
			if ("utf-8".equalsIgnoreCase(fileEncode)) {
			      mindjetData = parseData.parse(file);
			    }else {
			        String msg = FileUtils.readFileToString(file, fileEncode);
			        String path = "E:\\吉林市商务局1.txt";
			        File files = new File(path);
			        FileUtils.write(files, msg, "utf-8");
			        mindjetData = parseData.parse(files);
			        file.delete();
			      }

			    String json = write(mindjetData);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	 public static String write(MindjetData mindjetData)
	  {
	    JSONObject jsObj = new JSONObject();

	    jsObj.put("id", UUID.randomUUID().toString());
	    jsObj.put("title", mindjetData.getTitle());

	    JSONObject dateObj = new JSONObject();
	    Map map1 = new HashMap();
	    Long modifyTime = Long.valueOf(System.currentTimeMillis());
	    map1.put("created", modifyTime);
	    map1.put("modified", modifyTime);
	    dateObj.putAll(map1);
	    jsObj.put("dates", dateObj);

	    JSONObject dimenObj = new JSONObject();
	    Map map2 = new HashMap();
	    map2.put("x", "1000");
	    map2.put("y", "2000");
	    dimenObj.putAll(map2);
	    jsObj.put("dimensions", dimenObj);

	    JSONObject mindmap = new JSONObject();

	    JSONObject rootObj = new JSONObject();
	    rootObj.put("id", mindjetData.getUuid());
	    rootObj.put("parentId", "<null>");

	    rootObj.put("foldChildren", "true");
	    rootObj.put("branchColor", "#000000");

	    JSONObject textObj = new JSONObject();
	    textObj.put("caption", mindjetData.getTitle());

	    JSONObject fontObj = new JSONObject();
	    Map fontMap = new HashMap();
	    fontMap.put("style", "normal");
//	    fontMap.put("weight", "bold");
	    fontMap.put("weight", "bold");
//	    fontMap.put("decoration", "none");
	    fontMap.put("decoration", "none");
	    fontMap.put("size", "20");
	    fontMap.put("color", "#000000");
	    fontObj.putAll(fontMap);

	    textObj.put("font", fontObj);
	    rootObj.put("text", textObj);

	    JSONObject offsetObj = new JSONObject();
	    Map offsetMap = new HashMap();
	    offsetMap.put("x", "100");
	    offsetMap.put("y", "100");
	    offsetObj.putAll(offsetMap);
	    rootObj.put("offset", offsetObj);

	    JSONArray childrenArray = new JSONArray();
	    print(mindjetData.getNodeDataMap(), childrenArray, mindjetData);
	    rootObj.put("children", childrenArray);

	    mindmap.put("root", rootObj);
	    jsObj.put("mindmap", mindmap);

	    return jsObj.toString();
	  }
	 private static void print(Map<String, NodeData> nodeDataMap, JSONArray childrenArray, MindjetData mindjetData)
	  {
	    Iterator iter = nodeDataMap.keySet().iterator();
	    while (iter.hasNext()) {
	      String key = (String)iter.next();
	      NodeData node = (NodeData)nodeDataMap.get(key);
	      if (node.getParentId() == null) {
	        JSONObject obj = new JSONObject();
	        obj.put("id", node.getUuid());
	        obj.put("parentId", mindjetData.getUuid());
	        obj.put("foldChildren", "true");
	        obj.put("branchColor", "#000000");

	        JSONObject textObj = new JSONObject();
	        textObj.put("caption", node.getLabel());

	        JSONObject fontObj = new JSONObject();
	        Map fontMap = new HashMap();
	        fontMap.put("style", "normal");
	        fontMap.put("weight", "bold");
	        fontMap.put("decoration", "none");
	        fontMap.put("size", "20");
	        fontMap.put("color", "#000000");
	        fontObj.putAll(fontMap);

	        textObj.put("font", fontObj);
	        obj.put("text", textObj);

	        JSONObject offsetObj = new JSONObject();
	        Map offsetMap = new HashMap();
	        offsetMap.put("x", "1000");
	        offsetMap.put("y", "1000");
	        offsetObj.putAll(offsetMap);
	        obj.put("offset", offsetObj);

	        print(node.getChildNodeList(), obj);

	        childrenArray.add(obj);
	      }
	    }
	  }
	
	 private static void print(List<NodeData> nodeList, JSONObject obj)
	  {
	    if (nodeList.isEmpty()) {
	      return;
	    }

	    JSONArray childrenArray = new JSONArray();

	    for (Iterator localIterator = nodeList.iterator(); localIterator.hasNext(); ) { NodeData node = (NodeData)localIterator.next();

	      JSONObject childrenObj = new JSONObject();
	      childrenObj.put("id", node.getUuid());
	      childrenObj.put("parentId", node.getParentUUID());
	      childrenObj.put("foldChildren", "true");
	      childrenObj.put("branchColor", "#000000");

	      JSONObject textObj = new JSONObject();
	      textObj.put("caption", node.getLabel());

	      JSONObject fontObj = new JSONObject();
	      Map fontMap = new HashMap();
	      fontMap.put("style", "normal");
	      fontMap.put("weight", "bold");
	      fontMap.put("decoration", "none");
	      fontMap.put("size", "20");
	      fontMap.put("color", "#000000");
	      fontObj.putAll(fontMap);

	      textObj.put("font", fontObj);
	      childrenObj.put("text", textObj);

	      JSONObject offsetObj = new JSONObject();
	      Map offsetMap = new HashMap();
	      offsetMap.put("x", "1000");
	      offsetMap.put("y", "1000");
	      offsetObj.putAll(offsetMap);
	      childrenObj.put("offset", offsetObj);

	      if (!(node.getChildNodeList().isEmpty())) {
	        print(node.getChildNodeList(), childrenObj);
	      }

	      childrenArray.add(childrenObj);
	    }

	    obj.put("children", childrenArray);
	  }
	public ParseDataInterface getParseData() {
		return parseData;
	}

	public void setParseData(ParseDataInterface parseData) {
		this.parseData = parseData;
	}

}
