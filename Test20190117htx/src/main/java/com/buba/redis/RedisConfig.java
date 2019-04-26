package com.buba.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * Redis连接池 本地单机redis端口号6379
 * */
@SpringBootConfiguration
@PropertySource(value = {"classpath:redis.properties"})
public class RedisConfig {
	@Value("${spring.redis.maxTotal}")
    private Integer maxTotal;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    
    @Bean
    public JedisPool jedisPool(){
    	JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        JedisPool pool =  new JedisPool(poolConfig,host,port);
        return pool;
    }

}
