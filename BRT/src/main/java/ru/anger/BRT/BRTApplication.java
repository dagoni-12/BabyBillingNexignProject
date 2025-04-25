package ru.anger.BRT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BRTApplication {


    public static void main(String[] args) {
        System.out.println(org.hibernate.Version.getVersionString());
        SpringApplication.run(BRTApplication.class, args);
    }
}
