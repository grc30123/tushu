package com.example.tushu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan("com.example.tushu.Mapper")
public class TushuApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(TushuApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        System.out.println("Local:http://localhost:"+port);

    }

}
