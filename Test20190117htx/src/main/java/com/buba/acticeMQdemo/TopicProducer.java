package com.buba.acticeMQdemo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class TopicProducer {
	public static void main(String[] args) throws Exception {
//		第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.64.129:61616");
//		第二步：使用ConnectionFactory对象创建一个Connection对象。
		Connection conn = connectionFactory.createConnection();
//		第三步：开启连接，调用Connection对象的start方法。
		conn.start();
//		第四步：使用Connection对象创建一个Session对象。
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Topic对象。
		Topic topic = session.createTopic("TopicTest");
//		第六步：使用Session对象创建一个Producer对象。
		MessageProducer messageProducer = session.createProducer(topic);
//		第七步：创建一个Message对象，创建一个TextMessage对象。
		TextMessage textMessage = new ActiveMQTextMessage();
		textMessage.setText("我是topic发布的消息");
//		第八步：使用Producer对象发送消息。
		messageProducer.send(textMessage);
//		第九步：关闭资源。
		messageProducer.close();
		session.close();
		conn.close();
	}
}
