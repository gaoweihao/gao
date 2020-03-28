package xxzgssdb;

import org.nutz.ssdb4j.spi.SSDB;

import ssdb.SSDBPoolFactory;

public class UserInfoSSDB {
	/**
	 * 学习中国缓存账号和手机号
	 * */
	public static void saveUserInfoMessage(String account,String mobile) {
		SSDB conn=null;
		try{
			conn = SSDBPoolFactory.getMaster();
			if(null!=conn){
				conn.hset("study:xxzg:user:mobile:account", mobile,account);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 学习平台缓存账号和手机号
	 * */
	public static void saveStudyUserInfoMessage(String account,String mobile) {
		SSDB conn=null;
		try{
			conn = SSDBPoolFactory.getMaster();
			if(null!=conn){
				conn.hset("study:user:mobile:account", mobile,account);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

}
