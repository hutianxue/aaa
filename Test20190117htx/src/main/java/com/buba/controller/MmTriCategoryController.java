package com.buba.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.buba.bean.Company;
import com.buba.bean.MmTriCategory;
import com.buba.service.MmTriCategoryService;

@Controller
public class MmTriCategoryController {
	@Autowired
	private  MmTriCategoryService mmTriCategoryService;
	
	@ResponseBody
	@RequestMapping("/findAll")
	public List<MmTriCategory> findAll(){
		//查询全部
		List<MmTriCategory> findAll = mmTriCategoryService.findAll();
		return findAll;
	}
	//导出xml文件
	@RequestMapping("/xmlText")
	public void xmlText() {
		List<MmTriCategory> findAll = mmTriCategoryService.findAll();
		//转XML
		//StringBuffer初始容量为16字符
		StringBuffer xml = new StringBuffer();
		//拼入StringBuffer
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<MmTriCategorys>\n");
		for (MmTriCategory mmTriCategory : findAll) {
			xml.append(mmTriCategory+"\n");
		}
		 xml.append("</MmTriCategorys>");
		 //在指定地址创建xml文件
		 File file = new File("D:/chuan1/xmlTest.xml");
		 try {
			 //打印流  只能输出 输出到刚才创建的那个文件里
			PrintStream ps = new PrintStream(new FileOutputStream(file));
			try {
				//将拼接好的xml转换成字符串，以字符的方式打印到指定文件中 编码格式为utf-8
				ps.println(new String(xml.toString().getBytes(),"utf-8"));
				ps.flush();
				ps.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	//解析xml文件
	@RequestMapping("/dom4jTest")
	public void dom4jTest() throws DocumentException, FileNotFoundException {
		//为了能够逐行解析xml文档，因为单纯的dom4j解析需要把整个文档加载
		SAXReader reader = new SAXReader();		
		Document document = null;
		//获取文件 转成流
		File f = new File("D:/chuan1/demo01.xml");
		InputStream in = new FileInputStream(f);
		//转成 Document对象
		Document doc = reader.read(in);
		//获取根节点
		Element root = doc.getRootElement();
		//迭代器迭代
		Iterator<Element> it = root.elementIterator();
        while(it.hasNext()){
        	Element e = it.next();//获取子元素
        	//获取某个元素中的某个属性
        	Attribute idAttr = e.attribute("id");
        	//获取该属性的值
            String id = idAttr.getValue();
            //通过元素对象获取子元素对象
            Element nameElement = e.element("name");
            //获取元素中的文本内容
            String name = nameElement.getText();
            //获取公司地址
            Element addressElement = e.element("address");
            String address = addressElement.getText();
            //封装对象
            Company company = new Company();
            company.setId(Integer.parseInt(id));
            company.setName(name);
            company.setAddress(address);
            System.out.println(company); 
        }
	}
	//StringBoot整合redis
	@RequestMapping("/redis")
	public void findRedis() {
		//控制台打印获取到的value值
	    System.out.println(mmTriCategoryService.findRedis());
	}
}
