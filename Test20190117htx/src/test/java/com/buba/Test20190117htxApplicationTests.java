package com.buba;

import java.lang.reflect.Method;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.buba.service.MmTriCategoryService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class Test20190117htxApplicationTests {

	@Test
	public void testQueueProducer() {
/*//		第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。61616是默认端口
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp:http://192.168.64.129:61616");
//		第二步：使用ConnectionFactory对象创建一个Connection对象。
		Connection conn = connectionFactory.createConnection();
//		第三步：开启连接，调用Connection对象的start方法。
		conn.start();
//		第四步：使用Connection对象创建一个Session对象。
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Queue对象。
		Queue queue = session.createQueue("queueTest");
//		第六步：使用Session对象创建一个Producer对象。
		MessageProducer messageProducer = session.createProducer(queue);
//		第七步：创建一个Message对象，创建一个TextMessage对象。
		TextMessage message = session.createTextMessage("这是queue创建的信息");
//		第八步：使用Producer对象发送消息。
		messageProducer.send(message);
//		第九步：关闭资源。
		messageProducer.close();
		session.close();
		conn.close();*/
		
	}
	
	@Test
	public void testC() {
		
	}

}

