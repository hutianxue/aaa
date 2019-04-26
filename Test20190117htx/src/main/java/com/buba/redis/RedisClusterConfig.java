package com.buba.redis;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@SpringBootConfiguration
@PropertySource(value = {"classpath:redisCluster.properties"})
public class RedisClusterConfig {
	    //注入集群节点信息：即application.properties中的spring.redis.cluster.nodes
		@Value("${spring.redis.cluster.nodes}")
		private String clusterNodes;
		@Bean
		public JedisCluster getJedisCluster(){
			//创建一个数组  将ip和端口号的组合以“，”分割后存入该数组
			String cNodes[]=clusterNodes.split(",");
			//集群节点列表（set集合不可重复）
			Set<HostAndPort> nodes=new HashSet<>();
			for(String node:cNodes){
				//通过循环，将cNode集合的数据以“：”为分割条件分隔开存入hp集合
				String hp[]=node.split(":");
				//循环往集群节点列表中添加节点（其实node集合分割后每一组就只有两个值，也就是下标只有0/1）
				nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));	
			}
			//创建集群对象  将节点全都添加进去
			JedisCluster jedisCluster=new JedisCluster(nodes);
			//返回这个集群对象
			return	jedisCluster;
		}
}
