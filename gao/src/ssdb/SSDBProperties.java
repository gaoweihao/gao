package ssdb;


import java.io.IOException;

import java.util.Properties;


public class SSDBProperties {

	//private static Logger logger = Logger.getLogger(SSDBProperties.class);
	
	private SSDBProperties(){
		
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
	            props.load(SSDBProperties.class.getResourceAsStream("/ssdb.properties"));
	        }catch (IOException e) {  
	            e.printStackTrace();  
	            //logger.error(e.getMessage());
	        }
		}
		return props.getProperty(key);
    }  
}
