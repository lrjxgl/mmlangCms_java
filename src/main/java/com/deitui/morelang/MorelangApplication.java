package com.deitui.morelang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(nameGenerator = UniqueNameGenerator.class)
public class MorelangApplication {

    public static void main(String[] args) {
        SpringApplication.run(MorelangApplication.class, args);
    	//SpringApplication.run(BeanNameConflictApplication.class, args);
    }

}
