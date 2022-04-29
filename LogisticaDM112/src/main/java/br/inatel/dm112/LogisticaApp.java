package br.inatel.dm112;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "br.inatel.client.*") })
@EnableSpringDataWebSupport
@EnableSwagger2
public class LogisticaApp {

    public static void main(String[] args) {
        SpringApplication.run(LogisticaApp.class, args);
    }

}
