package json;

import java.util.List;

import bean.UserBaseInfo;

import com.google.common.collect.Lists;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;

public class TestJsoniter {

	/**
	 * 将对象的集合转换为json格式的字符串
	 * */
	public static String  getJsonString(){
		List<UserBaseInfo> userList=Lists.newArrayList();
		for(int i=0;i<100;i++){
			UserBaseInfo user=new UserBaseInfo(i+1,"小明"+i);
			userList.add(user);
		}
		String jsoniter=JsonStream.serialize(userList);
	    System.out.println(jsoniter);
	  return jsoniter;
	}
	
	/**
	 *将json格式的对象字符串转化为对象
	 * */
	public static <T> T getListObject1(String jsoniter,Class<T> obj){
		T array = JsonIterator.deserialize(jsoniter, obj);
		return array;
	}
	
	
	public static void main(String[] args) {
		String json=getJsonString();
		System.out.println(json.length());
		getListObject1(json, UserBaseInfo[].class);
		/*UserBaseInfo[] u = getListObject1(getJsonString(), UserBaseInfo[].class);
		System.out.println(u[0].getUserName());
	   */
	  /*  JsonIterator iter = JsonIterator.parse("[123, {'id': 0,'userName':'this error'}]".replace('\'', '"'));  
	    try {  
	        iter.readArray();  
	        System.out.println(iter.readInt());  
	        iter.readArray();  
	        UserBaseInfo err= iter.read(UserBaseInfo.class);  
	        //iter.readArray(); // end of array  
	        System.out.println(err.getUserName());
	        System.out.println(JsonStream.serialize(new int[]{1,2,3}));//输出 [1,2,3]  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	*/
	 }
	
}
