package testSSDB;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.JdbcUtil;
import bean.ResourcePraise;

public class TestDhxxSSDB {
	public static void main(String[] args) {
		try {
			QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
			String sql ="SELECT resourceId,up FROM T_RESOURCEPRAISE";
			Object[] params={};
			
			List<ResourcePraise> list=runner.query(sql, new BeanListHandler<ResourcePraise>(ResourcePraise.class),params);
			Long l=System.currentTimeMillis();
			System.out.println(list.size());
			for(ResourcePraise bean:list){
				DhxxResourcePariseSSDB.findNodesSumResourceByUuid(bean.getResourceId(), bean.getUp());
			}
			System.out.println(System.currentTimeMillis()-l);
			
         
		} catch (SQLException e1) {
			e1.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}

}
