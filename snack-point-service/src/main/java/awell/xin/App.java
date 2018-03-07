package awell.xin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
@EnableDiscoveryClient
@EnableFeignClients
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}