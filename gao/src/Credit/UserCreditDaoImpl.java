package Credit;
import bean.UserBaseInfo;
import bean.UserCreditYear;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;
import ssdb.SSDBPoolFactory;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class UserCreditDaoImpl {
/*现在有这样的一个需求：
    我在ssdb里存了用户的积分信息，使用的hash，存储的名字是：xxzg:point:用户的ID。
    我现在想把每个用户的参加“树家风活动的积分取出来”，存到一张表里。
    树家风活动的积分在用户积分信息hash里存在的位置是，key为11
    用户信息的表是：T_USERBASEINFO
    用户 几分 哪一年 版本 ID
T_USERCREDITYEAR
*/         
	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		
	    Calendar cal = Calendar.getInstance();
	    QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
    	String sql="";
    	
		try {
			//在数据库中获得用户的信息
		    List<UserBaseInfo> ubiList = getUserBaseInfoList();
			 //批处理添加数据
			   /* Object[][] params1=new Object[ubiList.size()][];
			    for(int i=0;i<ubiList.size();i++){
			    	sql="INSERT INTO T_USERCREDITYEAR(USERNAME,USERCREDIT,YEAR,USERID) VALUES(?,?,?,?)";
			    	params1[i]=new Object[]{ubiList.get(i).getUserName(),credit,cal.get(Calendar.YEAR),ubiList.get(i).getId()};
			    }
				runner.batch(sql, params1);
				*/
			
			//未使用批处理添加数据
		    for(UserBaseInfo u:ubiList){
		    	int credit=getUserCreditYear(u.getId());//积分
		    	//long id= getUserCreditByUserId(u.getId());//查询T_USERCREDITYEAR表中是否有该用户的积分信息
		    	//if(id==0){//如果ID不存在則添加信息
	        	sql="INSERT INTO T_USERCREDITYEAR(USERNAME,USERCREDIT,YEAR,USERID) VALUES(?,?,?,?)";
    	        Object[] params={u.getUserName(),credit,cal.get(Calendar.YEAR),u.getId()};
	   			runner.update(sql,params);
		    	/*}else{//如果ID存在則修改信息
        		    sql="UPDATE T_USERCREDITYEAR SET YEAR=? , USERCREDIT=? WHERE ID=?";
        		    Object[] params1={cal.get(Calendar.YEAR),credit,id};
					runner.update(sql, params1);
					
	    	   } */
	       }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
	    long endTime=System.currentTimeMillis();
	    System.out.println((endTime-startTime)/1000+"s");
	    
	}
	
	
	
	
	/**
	 * 通过用ID查询用户积分表中是否存在该用户的信息
	 * @throws SQLException 
	 * */
	public static long getUserCreditByUserId(long userId) throws SQLException{
		QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
		long id = 0;
		String sql = "SELECT ID FROM T_USERCREDITYEAR WHERE USERID=?";
		Object[] params={userId};
		UserCreditYear usercredit=runner.query(sql,new BeanHandler<UserCreditYear>(UserCreditYear.class),params);
			if(usercredit!=null){
				id=usercredit.getId();;
			}
		return id;
	}
	/**
	 * 在ssdb中查询所需要的信息
	 * */
	public static int getUserCreditYear(long id){
		SSDB conn = SSDBPoolFactory.getMaster();
		int credit =0;
		Response res=conn.hget("xxzg:point:"+id, 11);
			if(res.ok()){
				credit=res.asInt();
			}
		return credit;
	}
	/**
	 * 得到所有的用户的信息
	 * @throws SQLException 
	 * */
	public static List<UserBaseInfo> getUserBaseInfoList() throws SQLException{
		QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
        String sql ="SELECT ID,USERNAME FROM T_USERBASEINFO";
        Object[] params={};
        List<UserBaseInfo> ubiList=runner.query(sql, new BeanListHandler<UserBaseInfo>(UserBaseInfo.class),params);
		return ubiList;
	}
}
