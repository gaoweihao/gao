package xxzg;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.JdbcUtil;
import bean.ResourcereadPosition;
/**
 * 资源访问位置、资源阅读历史数据迁移
 * */
public class ActivityLwpraise {

	public static void main(String[] args) {
		try {
		QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
		/*String sql ="SELECT userId,talkId FROM T_ACTIVITY_LWPRAISE";
			Object[] params={};
			List<Lwpraise> list=runner.query(sql, new BeanListHandler<Lwpraise>(Lwpraise.class),params);
			System.out.println(list.size());
			
			Map<Long, String> map=Maps.newHashMap();
			
			for(Lwpraise bean:list){
				if(null==map.get(bean.getUserId())){
					map.put(bean.getUserId(),","+bean.getTalkid());
				}else {
					map.put(bean.getUserId(),map.get(bean.getUserId())+","+bean.getTalkid());
				}
				
			}
			
			
			for(Long key:map.keySet()){
				//ActivityLwpraiseSSDB.saveTalkIds(key, map.get(key));
				System.out.println(key+":"+map.get(key));
			}*/
			Object[] params={};
			String sql1 ="SELECT USERID, RESOURCEID,POSITION FROM T_RESOURCEREADPOSITION";
			List<ResourcereadPosition> postlist=runner.query(sql1, new BeanListHandler<ResourcereadPosition>(ResourcereadPosition.class),params);
			System.out.println(postlist.size());
			if(null!=postlist){
				for(ResourcereadPosition bean:postlist){
					//ActivityLwpraiseSSDB.savepostion(bean.getUserId(), bean.getResourceId(),bean.getPosition());
					System.out.println(""+bean.getUserId()+":" +bean.getResourceId()+":"+bean.getPosition());
				}
			}

			
			
         
		} catch (SQLException e1) {
			e1.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
}
