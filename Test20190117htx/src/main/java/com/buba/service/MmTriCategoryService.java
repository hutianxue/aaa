package com.buba.service;

import java.util.List;

import com.buba.bean.MmTriCategory;

public interface MmTriCategoryService {
	//查询全部实验管理信息
	List<MmTriCategory> findAll();
	//springboot整合Redis
	public String findRedis();
}
