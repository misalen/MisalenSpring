package org.misalen.mq;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.misalen.mq.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringbootJmsApplicationTests {

	@Autowired
	private Producer producer;

	@Test
	public void contextLoads() throws InterruptedException {
		Destination destination = new ActiveMQQueue("mytest.queue");
		Msg msg =new Msg();
		for (int i = 0; i < 100; i++) {
			producer.sendMessage(destination, msg);
		}
		System.err.println("=========================================");
		//Thread.sleep(100000);
	}

}
