package com.buba.acticeMQdemo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * topic持久订阅
 * */
public class TopicCustomer2 {
	public static void main(String[] args) throws Exception {
//		第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。默认端口号是61616
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.64.129:61616");
//		第二步：使用ConnectionFactory对象创建一个Connection对象。
		Connection conn = connectionFactory.createConnection();
//		第三步：设置消费者ID
		conn.setClientID("htx");
//		第四步：使用Connection对象创建一个Session对象。
		Session session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
//		第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Topic对象。
		Topic topic = session.createTopic("TopicTest");
//		第六步：将订阅者持久化
		TopicSubscriber ts = session.createDurableSubscriber(topic, "TopicTest");
//		第七步：开启连接
		conn.start();
//		第八步：开启消息同步
		Message message=ts.receive();
		while(message!=null){
			TextMessage txtMsg=(TextMessage)message;
			//conn.createSession(true, Session.AUTO_ACKNOWLEDGE);需要提交
			
			//txtMsg.acknowledge(); 在非事务性会话中,消息何时被确认取决于创建会话时的应答模式(acknowledgement mode)
			session.commit();
			System.out.println("收到消息:"+ txtMsg.getText());
			message=ts.receive(1000L);
			session.close();
			conn.close();
		}
	}
}
