package www.lipei.springcloud.cloud_customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import www.lipei.springcloud.cloud_customer.entity.User;

@RestController
public class HystrixController {
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/customer/testHystrix")
	@HystrixCommand(fallbackMethod = "fallbackHystrix") //给该方法添加熔断器功能
	public User testHystrix(User user) {
		//user = this.restTemplate.exchange("http://cloudprivade/SolrSearchHystrix", HttpMethod.POST,new HttpEntity<>(user,null),User.class).getBody();
		user = this.restTemplate.exchange("http://localhost:7002/SolrSearchHystrix", HttpMethod.POST,new HttpEntity<>(user,null),User.class).getBody();
		
		return user;
	}
	
	public User fallbackHystrix(User user) {
		return new User("hello", "lipei");
	}
}
