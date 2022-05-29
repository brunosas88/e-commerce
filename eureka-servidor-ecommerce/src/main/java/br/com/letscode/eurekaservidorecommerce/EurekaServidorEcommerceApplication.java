package br.com.letscode.eurekaservidorecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServidorEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServidorEcommerceApplication.class, args);
	}

}
