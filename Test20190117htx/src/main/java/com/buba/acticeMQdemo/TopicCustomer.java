package com.buba.acticeMQdemo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;


public class TopicCustomer {
	public static void main(String[] args) throws Exception {
//		第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。默认端口号是61616
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.64.129:61616");
//		第二步：使用ConnectionFactory对象创建一个Connection对象。
		Connection conn = connectionFactory.createConnection();
//		第三步：开启连接，调用Connection对象的start方法。
		conn.start();
//		第四步：使用Connection对象创建一个Session对象。
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Topic对象。
		Topic topic = session.createTopic("TopicTest");
//		第六步：使用Session对象创建一个Consumer对象
		MessageConsumer consumer = session.createConsumer(topic);
//		第七步：接收消息。 new MessageListener()匿名引用 调用监听器 先进先出
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				if(message instanceof TextMessage) {
					TextMessage messaget = (TextMessage) message;
					try {
						System.out.println(messaget.getText());
						
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		Thread.sleep(10000);// 睡眠10秒钟。
//		第八步：关闭资源。
		consumer.close();
		session.close();
		conn.close();
	}
}
