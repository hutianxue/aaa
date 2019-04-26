package com.buba.acticeMQdemo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueCustomer {
	public static void main(String[] args) throws Exception {
//		消费者：接收消息。		
//		第一步：创建一个ConnectionFactory对象。
		ConnectionFactory connectionFa=new ActiveMQConnectionFactory("tcp://192.168.64.129:61616");
//		第二步：从ConnectionFactory对象中获得一个Connection对象。
		Connection conn = connectionFa.createConnection();
//		第三步：开启连接。调用Connection对象的start方法。
		conn.start();
//		第四步：使用Connection对象创建一个Session对象。
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		第五步：使用Session对象创建一个Destination对象。和发送端保持一致queue，并且队列的名称一致。
		Queue queue = session.createQueue("queueTest");
//		第六步：使用Session对象创建一个Consumer对象。
		MessageConsumer messageConsumer = session.createConsumer(queue);
//		第七步：接收消息。使用监听器的方式监听  异步
		messageConsumer.setMessageListener(new MessageListener() {			
			@Override
			public void onMessage(Message message) {
				if(message instanceof TextMessage) {
					TextMessage messaget = (TextMessage)message;
					try {
//		第八步：打印消息。
						System.out.println(messaget.getText());
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});

//		第九步：关闭资源
		messageConsumer.close();
		session.close();
		conn.close();
	}
}
