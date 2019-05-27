package www.lipei.springcloud.cloud_customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import www.lipei.springcloud.cloud_customer.entity.Admin;


@RestController
public class XmlController {
	
	@Autowired
	public RestTemplate template;
	
	@RequestMapping(value = "/customer/getXml" 
			, method = RequestMethod.POST , 
			consumes = MediaType.APPLICATION_XML_VALUE
			,produces = MediaType.APPLICATION_XML_VALUE)
	public Admin getXml(Admin admin) {
		
		return this.template.exchange("http://cloudprivade/getXml", HttpMethod.POST,new HttpEntity<>(admin,null),Admin.class).getBody();
	} 
	
}
