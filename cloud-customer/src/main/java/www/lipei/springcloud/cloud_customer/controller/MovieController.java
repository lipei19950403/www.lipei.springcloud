package www.lipei.springcloud.cloud_customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import www.lipei.springcloud.cloud_customer.FeignInterFace.PayFeignClient;
import www.lipei.springcloud.cloud_customer.entity.User;


@RestController
public class MovieController {
	
	@Autowired
	private PayFeignClient payFeignClient;
	@Autowired
	public RestTemplate template;
	
	//使用object发送，无参数
	@GetMapping("/customer/object")
	public User sendObject(){
		
		User user = this.template.getForObject("http://cloudprivade/SolrSearch", User.class);
		System.out.println(user.getName());
		 return user;
	}
	
	//使用entity发送，无参数
	@GetMapping("/customer/entity")
	public User sendEntity(){
		ResponseEntity<User> resp = this.template.getForEntity("http://cloudprivade/SolrSearch", User.class);
		System.out.println(resp.getHeaders());
		System.out.println(resp.getStatusCode());
		 return resp.getBody();
	}
	
	
	//使用entity发送，无参数
	@GetMapping("/customer/entityById/{id}")
	public User sendEntityById(@PathVariable(name = "id")String id){
		ResponseEntity<User> resp = this.template.getForEntity("http://cloudprivade/SolrSearchById/{id}", User.class,id);
		System.out.println(resp.getHeaders());
		System.out.println(resp.getStatusCode());
		 return resp.getBody();
	}
	
	//使用object发送，无参数
	@GetMapping("/customer/objectById/{id}")
	public User sendObjectById(@PathVariable(name = "id")String id){
		User user = this.template.getForObject("http://cloudprivade/SolrSearchById/{id}", User.class,id);
		 return user;
	}
	
	@PostMapping("/customer/objectByPost/{type}")
	public User sendObjectByPost(User user, @PathVariable(name = "type") String type) {
		User res = this.template.postForObject("http://cloudprivade/SolrSearchByPost/{type}", user, User.class,type);
		return res;
	}
		
	@PostMapping("/customer/entityByPost/{type}")
	public User sendEntityByPost(User user, @PathVariable(name = "type") String type) {
		ResponseEntity<User> res = this.template.postForEntity("http://cloudprivade/SolrSearchByPost/{type}", user, User.class,type);
		return res.getBody();
	}
	
	@RequestMapping("/customer/entityByExchange/{type}")
	public User sendEntityByExchange(User user, @PathVariable(name = "type") String type) {
		ResponseEntity<User> res = this.template.exchange("http://cloudprivade/SolrSearchByPost/{type}", HttpMethod.POST,new HttpEntity<>(user,null), User.class,type);
		return res.getBody();
	}
	
	/*
	 * @RequestMapping(value="/customer/sendMapByChange" ,method=RequestMethod.POST
	 * ,consumes=MediaType.APPLICATION_JSON_VALUE
	 * ,produces=MediaType.APPLICATION_JSON_VALUE) public
	 * MultiValueMap<String,String> sendMapByChange(@RequestBody
	 * MultiValueMap<String,String> param){ return
	 * this.template.exchange("http://cloudprivade/SolrSearchMapByChange/",
	 * HttpMethod.POST,new HttpEntity<>(param,null),MultiValueMap.class).getBody();
	 * 
	 * }
	 */
	
	@RequestMapping(value="/customer/sendMapByChange"
			,method=RequestMethod.POST)
	public String sendMapByChange(User user){
		MultiValueMap<String,String> param = new LinkedMultiValueMap<>();
		param.set("name", user.getName());
		param.set("pwd", user.getPassword());
		param = this.template.exchange("http://cloudprivade/SolrSearchMapByChange", HttpMethod.POST,new HttpEntity<>(param,null),MultiValueMap.class).getBody();
		return param.get("name").toString();
		
	}
	
	@RequestMapping("/customer/feignTest")
	public String sendParamByFeign(@RequestParam String transSeqno,@RequestParam String checkStatus ) {
		return payFeignClient.payWithParams(transSeqno, checkStatus);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
}
