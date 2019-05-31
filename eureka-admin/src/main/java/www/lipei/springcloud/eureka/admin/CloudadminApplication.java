package www.lipei.springcloud.eureka.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * admin启动类
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class CloudadminApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(CloudadminApplication.class, args);
    }
}
