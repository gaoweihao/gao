package testSSDB;

import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;

import ssdb.SSDBPoolFactory;

public class TestMoleResourceSSDB {
public static void main(String[] args) {
	System.out.println(findNodesSumResourceByUuid("",100));
	
	
}

public static String findNodesSumResourceByUuid(String uuid,int sum){
	
	SSDB conn=null;
	Response response = null;
	try{
		conn = SSDBPoolFactory.getMaster();
		if(null!=conn){
			/*for(int i=6;i<1000;i++){
				conn.hdel("zsfzs:nodes:resource:num:15096064850010024626778701819534006308",i );
			}*/
			response=conn.hget("zsfzs:nodes:resource:num:15096064850010024626778701819534006308", 7);
			
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}
	if(null!=response.listString()&&response.listString().size()>0){
		return response.asString();
	}
	return null;
	
}
}