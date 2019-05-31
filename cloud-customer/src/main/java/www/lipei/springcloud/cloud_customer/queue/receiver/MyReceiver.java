package www.lipei.springcloud.cloud_customer.queue.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class MyReceiver {
	
	private static Logger log = LoggerFactory.getLogger(MyReceiver.class);
	
	@RabbitHandler
	public void receiver(String data) {
		log.info("receiver:" + data);
	}
}
