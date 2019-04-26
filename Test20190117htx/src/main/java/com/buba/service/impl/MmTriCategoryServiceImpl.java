package com.buba.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buba.bean.MmTriCategory;
import com.buba.mapper.MmTriCategoryMapper;
import com.buba.service.MmTriCategoryService;

import redis.clients.jedis.JedisCluster;
@Service
public class MmTriCategoryServiceImpl implements MmTriCategoryService {
	@Autowired
	private  MmTriCategoryMapper mmTriCategoryMapper;
	@Autowired
	private JedisCluster jedisCluster;
	@Override
	public List<MmTriCategory> findAll() {
		
		List<MmTriCategory> findAll = mmTriCategoryMapper.findAll();
		for (MmTriCategory mmTriCategory : findAll) {
			//根据id不同，赋不同的图片
			if(mmTriCategory.getId()==0) {
				mmTriCategory.setIcon("/static/images/outlook6.gif");
			}else {
				mmTriCategory.setIcon("/static/images/frametree01.gif");
			}
		}
		
		return findAll;
	}
	@Override
	public String findRedis() {
		//往节点里存数据
	    jedisCluster.set("userName", "胡天雪");
	    //返回键为userName的值
	    return jedisCluster.get("userName");
	}

}
