package www.lipei.cloud.provide.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import www.lipei.cloud.provide.entity.User;


@RestController
public class MovieController {
	
	@Value("${server.port}")
	private String port;
	//无参数接受请求
	@RequestMapping("/SolrSearch")
    public User SolrSearch(){
        return new User("lipei", "12345");
    }
	
	//有参数接受请求
	@RequestMapping("/SolrSearchById/{id}")
	public User SolrSearchById(@PathVariable(name = "id")String id) {
		 return new User("lipei", id);
	}
	
	//有参数接受请求
	@RequestMapping("/SolrSearchByPost/{type}")
	public User SolrSearchByPost(@RequestBody User user,@PathVariable(name = "type")String type) {
		 return new User(user.getName(),type);
	}
	
	@RequestMapping("/SolrSearchMapByChange")
	public MultiValueMap<String, String> SolrSearchMapByChange(@RequestBody MultiValueMap<String, String> param){
		param.set("name", port);
		return param;
	}
	
	@RequestMapping("/payWithParams")
	public String SolrSearchWithParams(@RequestParam String transSeqno,@RequestParam String checkStatus) {
		return transSeqno + checkStatus;
	}
	
	
}
