package redis;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 解析redis。properties文件
 * @author mayannan
 *
 */
public class RedisProperties {
	
	private static Logger logger = Logger
			.getLogger(RedisProperties.class);
	
	private RedisProperties(){
		
	}
	
	private static Properties props = null;

	/**
	 * 得到属性内容
	 * @param propertiesFileName
	 * @param key
	 * @return
	 */
	public static synchronized String getPropertyValue(String key) {  
		if(props == null){ 
	        try {  
	        	props = new Properties();
	            props.load(RedisProperties.class.getResourceAsStream("/redis.properties"));
	        }catch (IOException e) {  
	            e.printStackTrace();  
	            logger.error(e.getMessage());
	        }
		}
		return props.getProperty(key);
    }  
}

