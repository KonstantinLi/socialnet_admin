package ru.skillbox.adminpanel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ConfigurationPropertiesScan
//@EnableScheduling
@Configuration
@EnableAspectJAutoProxy
public class SocialNetAdminPanel {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetAdminPanel.class, args);
    }

}
