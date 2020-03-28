package xxzgssdb;

import org.nutz.ssdb4j.spi.SSDB;

import ssdb.SSDBPoolFactory;

public class ActivityLwpraiseSSDB {
	
	public static void saveHistory(Long userId, Long resourceId, int times) {
		SSDB conn=null;
		try{
			conn = SSDBPoolFactory.getMaster();
			if(null!=conn){
				conn.zset("xxzg:view:"+userId, resourceId,times);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		
	}
	
	//资源访问位置、资源阅读历史数据迁移
	public static void savepostion(Long userId,Long resourceid , String postion){
		SSDB conn=null;
		try{
			conn = SSDBPoolFactory.getMaster();
			if(null!=conn){
				conn.hset("xxzg:pos:"+userId, resourceid,postion);	
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	public static void saveTalkIds(Long userId,String talkids){
		SSDB conn=null;
		try{
			conn = SSDBPoolFactory.getMaster();
			if(null!=conn){
				conn.hset("xxzg:lw:parise", userId,talkids);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	public static void findTalkIds(Long userId){
		SSDB conn=null;
		try{
			conn = SSDBPoolFactory.getMaster();
			if(null!=conn){
				conn.hget("xxzg:lw:parise", userId);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}

}
