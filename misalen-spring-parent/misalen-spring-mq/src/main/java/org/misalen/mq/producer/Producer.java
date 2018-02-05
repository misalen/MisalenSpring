package org.misalen.mq.producer;

import javax.jms.Destination;

import org.misalen.mq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service("producer")
public class Producer {
	@Autowired
	private JmsMessagingTemplate jmsTemplate;
	public void sendMessage(Destination destination, final Message message) {
		jmsTemplate.convertAndSend(destination, message);
	}
}