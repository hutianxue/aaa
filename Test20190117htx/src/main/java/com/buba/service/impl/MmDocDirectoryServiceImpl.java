package com.buba.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buba.bean.MmDocDirectory;
import com.buba.mapper.MmDocDirectoryMapper;
import com.buba.service.MmDocDirectoryService;
@Service
public class MmDocDirectoryServiceImpl implements MmDocDirectoryService {
	@Autowired
	private MmDocDirectoryMapper mmDocDirectoryMapper;
	@Override
	public List<MmDocDirectory> findAllDoc() {
		//方法一：直接在数据库里添加图片
		List<MmDocDirectory> allDoc = mmDocDirectoryMapper.findAllDoc();
		//方法二：可以遍历集合，根据id进行判断 数据库中添加跟节点 id为0 则当id为0的时候，赋一个图片，
		//其它id都是一样的id
		return allDoc;
	}

}
