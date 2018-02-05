package org.misalen.mq.consumer;

import org.misalen.mq.Message;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.db.DBAppender;

@Component
public class LogConsumer {
	ActiveMQProperties activeMQProperties;
	//DBAppender
	@JmsListener(destination = "mislaen.log")
	public void receiveQueue(Message a) throws InterruptedException {
		System.out.println("Consumer收到的报文为:" + a);
	}
}