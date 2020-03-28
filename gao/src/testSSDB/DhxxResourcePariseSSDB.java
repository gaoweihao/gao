package testSSDB;

import org.nutz.ssdb4j.spi.SSDB;

import ssdb.SSDBPoolFactory;

public class DhxxResourcePariseSSDB {

	public static void findNodesSumResourceByUuid(Long resourceId,int up){
		SSDB conn=null;
		try{
			conn = SSDBPoolFactory.getMaster();
			if(null!=conn){
				conn.hset("dhxx:res:praise:2", resourceId,up);
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
}
