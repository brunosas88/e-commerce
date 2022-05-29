package br.com.letscode.usuarioapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableDiscoveryClient
public class UsuarioApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioApiApplication.class, args);
    }

}
