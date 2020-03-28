package redis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;


public class RedisPoolFactory {
	private static Logger logger = LoggerFactory.getLogger(RedisPoolFactory.class);
	
	private static ShardedJedisSentinelPool pool;

	static {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		List<String> masters = new ArrayList<String>();
		masters.add(RedisProperties.getPropertyValue("redis.shard1.name"));
		/*masters.add(RedisProperties.getPropertyValue("redis.shard2.name"));
		masters.add(RedisProperties.getPropertyValue("redis.shard3.name"));
		masters.add(RedisProperties.getPropertyValue("redis.shard4.name"));*/
		
		Set<String> sentinels = new HashSet<String>();
		sentinels.add(RedisProperties.getPropertyValue("redis.shard1.addr"));
		/*sentinels.add(RedisProperties.getPropertyValue("redis.shard2.addr"));
		sentinels.add(RedisProperties.getPropertyValue("redis.shard3.addr"));
		sentinels.add(RedisProperties.getPropertyValue("redis.shard4.addr"));*/
		
		config.setMaxWaitMillis(-1);
		config.setMaxIdle(0); 
		config.setMaxTotal(1000);
		pool = new ShardedJedisSentinelPool(masters, sentinels, config, 100000);//, RedisProperties.getPropertyValue("redis.pwd")
	}
	
	public static ShardedJedis getJedis() {
		try {
			if(null!=pool){
				return pool.getResource();
			}
		} catch (Exception e) {
			logger.error(
					"获取master-cache连接失败，原因："
							+ e.getMessage(), e);
		}
		return null;
	}

	public static void returnJedis(ShardedJedis shardedJedis) {
		pool.returnResource(shardedJedis);
	}

	public static void main(String[] args) {
		
	}
}

