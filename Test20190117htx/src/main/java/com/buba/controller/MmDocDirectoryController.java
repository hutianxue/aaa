package com.buba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buba.bean.MmDocDirectory;
import com.buba.service.MmDocDirectoryService;

@Controller
public class MmDocDirectoryController {
	@Autowired
	private MmDocDirectoryService mmDocDirectoryService;
	
	@ResponseBody
	@RequestMapping("/docShow")
	public List<MmDocDirectory> docShow(){		
		//查询全部文件
		List<MmDocDirectory> findAllDoc = mmDocDirectoryService.findAllDoc();
		return findAllDoc;
	}
	
}
