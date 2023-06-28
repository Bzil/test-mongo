package bz.kata.db;

import bz.kata.document.DocumentConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        DocumentConfig.class,
})
@ComponentScan(basePackages = {"bz.kata.db"})
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
public class MongoDb {
    public static void main(String[] args) {
        SpringApplication.run(MongoDb.class, args);
    }

}
