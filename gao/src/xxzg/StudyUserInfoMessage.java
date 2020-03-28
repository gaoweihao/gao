package xxzg;

import bean.UserBaseInfo;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUtil;
import xxzgssdb.UserInfoSSDB;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 学习平台查询用户account 和mobile 的对应关系
 * 要求：查询mobile只出现一次的list并将其缓存到ssdb中
 *       查询所有的用户
 */
public class StudyUserInfoMessage {
    public static void main(String[] args) {
        try {
            long l=System.currentTimeMillis();
            QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
            Object[] params={};
            String sql ="SELECT ACCOUNT,MOBILE FROM `T_USERBASEINFO` where DELSTATE=0";
            List<UserBaseInfo> userList =
                    runner.query(sql, new BeanListHandler<UserBaseInfo>(UserBaseInfo.class),params);
            Map<String,String> map= Maps.newHashMap();
            if(null!=userList){
                for(UserBaseInfo user:userList){
                    if(!Strings.isNullOrEmpty(user.getMobile())){
                        if(Strings.isNullOrEmpty(map.get(user.getMobile().trim()))){
                            map.put(user.getMobile().trim(),user.getAccount().trim());
                        }else{
                            map.remove(user.getMobile().trim());
                        }
                    }
                }
            }
            if (null!=map.keySet()){
                for(String key : map.keySet()){
                    UserInfoSSDB.saveStudyUserInfoMessage(map.get(key),key);
                    System.out.println("account:   "+map.get(key) +"  mobile  :  "+key);
                }
            }
            System.out.println(System.currentTimeMillis()-l);
            System.out.println("（电话未重复）用户总数：    "+map.keySet().size());
            System.out.println("用户总数：   "+userList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
