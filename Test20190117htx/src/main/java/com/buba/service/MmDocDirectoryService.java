package com.buba.service;

import java.util.List;

import com.buba.bean.MmDocDirectory;

public interface MmDocDirectoryService {
	//查询所有文档
	List<MmDocDirectory> findAllDoc();
}
