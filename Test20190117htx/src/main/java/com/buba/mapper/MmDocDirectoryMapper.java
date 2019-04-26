package com.buba.mapper;

import java.util.List;

import com.buba.bean.MmDocDirectory;

public interface MmDocDirectoryMapper {
	//查询所有文档
	List<MmDocDirectory> findAllDoc();
}
