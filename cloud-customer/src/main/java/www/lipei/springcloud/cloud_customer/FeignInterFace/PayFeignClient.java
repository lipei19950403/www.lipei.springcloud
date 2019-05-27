package www.lipei.springcloud.cloud_customer.FeignInterFace;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloudprivade")// 或者使用url属性指定请求的URL
public interface PayFeignClient {
	
	@GetMapping("/payWithParams")
	public String payWithParams(@RequestParam String transSeqno,@RequestParam String checkStatus);
	//方法参数必须用@RequestParam标记，否则被认为是RequestBody而抛个数太多的异常。
}
