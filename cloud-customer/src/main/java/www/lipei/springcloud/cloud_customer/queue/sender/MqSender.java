package www.lipei.springcloud.cloud_customer.queue.sender;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MqSender {
	
	private static Logger log = LoggerFactory.getLogger(MqSender.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send() {
		String context = "lipei" + new Date();
		log.info("send:" + context);
		amqpTemplate.convertAndSend("hello",context);
	}
	
}
