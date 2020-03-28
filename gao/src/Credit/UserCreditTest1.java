package Credit;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;

import com.google.common.base.Strings;

import ssdb.SSDBPoolFactory;
import util.JdbcUtil;
import bean.UserBaseInfo;
import bean.UserCreditYear;

public class UserCreditTest1 {
    static SSDB conn = SSDBPoolFactory.getMaster();
	static QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
	
	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		//HashMultimap<Object, Object> credmap=HashMultimap.create();
		try {
			//在数据库中获得用户的信息
			List<UserBaseInfo> ubiList = getUserBaseInfoList();
			for(UserBaseInfo u:ubiList){
			//保存缓存树家风活动积分
		    //int	credit =getUserCreditYear(u.getId());//积分
		    //System.out.println(u.getId() + "              "+ credit);
		    //String sql="INSERT INTO T_USERSCDCREDITYEAR(USERCREDIT,YEAR,USERID) VALUES(?,?,?)";
		    //保存缓存里总积分	
		    int	credit =getUserCreditYear1(u.getId());//积分
		    String sql="INSERT INTO T_USERCREDITYEAR(USERCREDIT,YEAR,USERID) VALUES(?,?,?)";
	        Object[] params={credit,cal.get(Calendar.YEAR),u.getId()};
   			runner.update(sql,params);
		    
	       }
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	    
	   
	    long endTime=System.currentTimeMillis();
	    System.out.println((endTime-startTime)/1000+"s");
	    
	}
	
	/**
	 * 通过用ID查询用户积分表中是否存在该用户的信息
	 * @throws SQLException 
	 * */
	public static long getUserCreditByUserId(long userId) throws SQLException{
		long id = 0;
		String sql = "SELECT ID FROM T_USERCREDITYEAR WHERE USERID=?";
		Object[] params={userId};
		UserCreditYear usercredit=runner.query(sql,new BeanHandler<UserCreditYear>(UserCreditYear.class),params);
			if(usercredit!=null){
				id=usercredit.getId();
			}
		return id;
	}
	/**
	 * 在ssdb中查询所需要的信息
	 * */
	public static int getUserCreditYear (long id){
		int credit =0;
		Response res =conn.hget("xxzg:point:"+id, 11);
		if(res.ok()){
			credit=res.asInt();
		}
		return credit;
	}
	/**
	 * 在ssdb中查询所需要的信息
	 * */
	public static int getUserCreditYear1 (long id){
		int credit =0;
		Response res =conn.hgetall("xxzg:point:"+id);
		if(res.ok()){
			Map<String, String> map = res.mapString();
			for(String key : map.keySet()){
				if(!"11".equals(key) && 
						!"0".equals(key) && !Strings.isNullOrEmpty(map.get(key))){
					credit += Integer.valueOf(map.get(key));
				}
			}
			System.out.println(id + "            " + credit);
		}
		return credit;
	}
	/**
	 * 得到所有的用户的信息
	 * @throws SQLException 
	 * */
	public static List<UserBaseInfo> getUserBaseInfoList() throws SQLException{
        String sql ="SELECT ID,USERNAME FROM T_USERBASEINFO";
        Object[] params={};
        List<UserBaseInfo> ubiList=runner.query(sql, new BeanListHandler<UserBaseInfo>(UserBaseInfo.class),params);
		System.out.println(ubiList.size());
		return ubiList;
	}
}
