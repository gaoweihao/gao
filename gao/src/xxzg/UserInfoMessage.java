package xxzg;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.JdbcUtil;
import xxzgssdb.UserInfoSSDB;
import bean.UserBaseInfo;

import com.google.common.base.Strings;
/**
 * ssdb 中缓存电话和账号的映射关系
 * */
public class UserInfoMessage {

	public static void main(String[] args) {
		try {
			long l=System.currentTimeMillis();
			QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
			Object[] params={};
			String sql ="SELECT ACCOUNT,MOBILE  FROM `T_USERBASEINFO` where DELSTATE=0 AND ISVALIDATE ='1'";
			List<UserBaseInfo> userList = 
					runner.query(sql, new BeanListHandler<UserBaseInfo>(UserBaseInfo.class),params);
			if(null!=userList){
				for(UserBaseInfo user:userList){
					if(!Strings.isNullOrEmpty(user.getMobile())){
						//UserInfoSSDB.saveUserInfoMessage(user.getAccount(), user.getMobile());
						System.out.println("account:   "+user.getAccount() +"  mobile  :  "+user.getMobile());
					}
				}
			}
			System.out.println(System.currentTimeMillis()-l);
		System.out.println(userList.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
}
