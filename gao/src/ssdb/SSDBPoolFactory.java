package ssdb;

import java.io.IOException;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.nutz.ssdb4j.SSDBs;
import org.nutz.ssdb4j.spi.SSDB;

public class SSDBPoolFactory {

	private static SSDB master=null; 
	
	static{
		GenericObjectPool.Config config = new GenericObjectPool.Config();
		config.maxActive=800;
		config.maxWait=-1;
		config.whenExhaustedAction=GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
		config.maxIdle=50;
		config.timeBetweenEvictionRunsMillis = 60000;
		config.testWhileIdle = true;
		master = SSDBs.pool(SSDBProperties.getPropertyValue("ssdb.master.ip"), 
				Integer.valueOf(SSDBProperties.getPropertyValue("ssdb.master.port")), 
				Integer.valueOf(SSDBProperties.getPropertyValue("ssdb.timeout")), config);
		
	}
	
	public static SSDB getMaster(){
		return master;
	}
	
	public static void closeConn(SSDB conn){
		if(null != conn){
			try {
				conn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
