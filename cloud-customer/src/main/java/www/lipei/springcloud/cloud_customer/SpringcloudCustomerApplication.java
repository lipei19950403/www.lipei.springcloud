package www.lipei.springcloud.cloud_customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
@EnableFeignClients//使用feign，使用restTemplate时，不必用这个
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//排除自动注入数据源的配置（取消数据库配置）
@EnableDiscoveryClient //springcloud客户端
@EnableHystrix//开启熔断器功能
@EnableHystrixDashboard//监控熔断器
public class SpringcloudCustomerApplication {
	
	@Bean
	@LoadBalanced//在注册中心里进行查找微服务,负载均衡
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	public static void main(String[] args) {   
		SpringApplication.run(SpringcloudCustomerApplication.class, args);
	}
	
	/**
	 * 初始化熔断器监控，不加会报错
	 * @return
	 */
	@Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
	
}

