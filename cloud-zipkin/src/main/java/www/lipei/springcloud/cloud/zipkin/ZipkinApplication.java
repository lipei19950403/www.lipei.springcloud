package www.lipei.springcloud.cloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * Hello world!
 *
 */
@SpringBootApplication
//@EnableZipkinServer
public class ZipkinApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ZipkinApplication.class, args);
    }
    
	/*
	 * @Bean public RestTemplate restTemplate() { return new RestTemplate(); }
	 */
}
