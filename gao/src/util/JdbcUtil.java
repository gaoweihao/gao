package util;

import java.beans.PropertyVetoException;

import com.alibaba.fastjson.serializer.DateSerializer;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {
		private static ComboPooledDataSource dataSource =new ComboPooledDataSource();
		
	      static {  
	    	    try {  
	    	        dataSource.setDriverClass("com.mysql.jdbc.Driver");  
	    	        dataSource.setJdbcUrl("jdbc:mysql://10.1.3.4:3306/jyxx?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8");
	    	        dataSource.setUser("xjzx");  
	    	        dataSource.setPassword("P#S^6b");
	    	        /*dataSource.setJdbcUrl("jdbc:mysql://10.1.3.48:3306/jyxx?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8");  
	    	        dataSource.setUser("xjzx");
	    	        dataSource.setPassword("xjzx_123");*/
	    	        //知识分子式
	    	        /*dataSource.setJdbcUrl("jdbc:mysql://10.1.2.172:3306/fenzi?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8");  
	    	        dataSource.setUser("fenzi");
	    	        dataSource.setPassword("fenzishi");*/
	    	      //知识分子是测试库
	    	        /*dataSource.setJdbcUrl("jdbc:mysql://10.1.3.156:3306/fenzishi?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8");  
	    	        dataSource.setUser("fenzishitest");
	    	        dataSource.setPassword("xjzx_123");*/
	    	        /*dataSource.setJdbcUrl("jdbc:mysql://10.1.3.59:3306/dhxx?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8");  
	    	        dataSource.setUser("dhxx_user");
	    	        dataSource.setPassword("SSr6H!@4ri_6");*/
	    	        //学习中国正式版本
	    	       /* dataSource.setJdbcUrl("jdbc:mysql://10.1.2.236:3306/xxzg?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8");
	    	        dataSource.setUser("studymgr");
	    	        dataSource.setPassword("study.2015");*/
	    	      //学习中国测试版
	    	        /*dataSource.setJdbcUrl("jdbc:mysql://10.1.2.48:3306/xxzg?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8");
	    	        dataSource.setUser("xjzx");
	    	        dataSource.setPassword("xjzx_123");*/
	    	        //学习平台
					/*dataSource.setJdbcUrl("jdbc:mysql://10.1.2.62:3306/studymanager?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8");
					dataSource.setUser("studymanager");
					dataSource.setPassword("xxpt_62");*/

	    	        dataSource.setMaxPoolSize(100);//设置最大的连接数  
	    	        dataSource.setMinPoolSize(1);//设置最小的连接数  
	    	        dataSource.setInitialPoolSize(5);//初始化连接数  
	    	        dataSource.setAcquireIncrement(5);//连接数的增量  
	    	    } catch (PropertyVetoException e) {
	    	        e.printStackTrace();  
	    	    } 
	      }
		
	      public static ComboPooledDataSource getDataSource(){
	      return dataSource;
	      }

}
