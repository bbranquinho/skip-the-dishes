package br.com.skip.the.dishes.command;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.skip.the.dishes.command")
@Configuration
public class CommandConfig {

}

