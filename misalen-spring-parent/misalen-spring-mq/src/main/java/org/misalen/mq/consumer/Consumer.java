package org.misalen.mq.consumer;

import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	ActiveMQProperties activeMQProperties;
	@JmsListener(destination = "mytest.queue")
	public void receiveQueue(String text) {
		System.out.println("Consumer收到的报文为:" + text);
	}
}