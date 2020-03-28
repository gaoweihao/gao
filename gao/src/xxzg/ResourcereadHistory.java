package xxzg;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.JdbcUtil;
import bean.ResourcereadHistorybean;
import bean.UserBaseInfo;
/**
 * 资源访问位置、资源阅读历史数据迁移
 * */
public class ResourcereadHistory {
	public static void main(String[] args) {
		try {
			long l=System.currentTimeMillis();
			QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
			Object[] params={};
			String sql ="SELECT distinct(u.ID)  FROM `T_USERBASEINFO` u INNER JOIN T_RESOURCEREADHISTORY r ON r.USERID=u.ID where u.DELSTATE=0";//"SELECT ID FROM `T_USERBASEINFO` where DELSTATE=0";
			List<UserBaseInfo> userList = 
					runner.query(sql, new BeanListHandler<UserBaseInfo>(UserBaseInfo.class),params);
			System.out.println(userList.size());
			if(null!=userList){
				for(UserBaseInfo user:userList){
					String sql1 ="SELECT USERID,RESOURCEID,TIMES "
							+ "FROM T_RESOURCEREADHISTORY "
							+ "where userid=? AND deleted='0' order by TIMES desc limit 40";
					Object[] params1={user.getId()};
					List<ResourcereadHistorybean> historyList = 
							runner.query(sql1, new BeanListHandler<ResourcereadHistorybean>(ResourcereadHistorybean.class),params1);
					if(null!=historyList&&historyList.size()>0){
						System.out.println(user.getId()+":"+historyList.size());
						for(ResourcereadHistorybean bean:historyList){
							//ActivityLwpraiseSSDB.saveHistory(bean.getUserId(),bean.getResourceId(),bean.getTimes()-1431964800);
							System.out.println(""+bean.getUserId()+":" +bean.getResourceId()+":"+bean.getTimes());
						}
					}
				}
			}
			System.out.println(System.currentTimeMillis()-l);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
		
}
