package com.mahdi.website;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories(basePackages = {"com.mahdi.website"})
@EnableMongoRepositories(basePackages = {"com.mahdi.website"})
@EnableAspectJAutoProxy
public class GhomshehWebsite {

    public static void main(String[] args) {
        SpringApplication.run(GhomshehWebsite.class, args);
    }

}