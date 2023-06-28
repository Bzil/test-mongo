package bz.kata.app;

import bz.kata.document.offer.OfferConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        OfferConfig.class,
})
@ComponentScan(basePackages = {"bz.kata.app"})
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
public class MongoApp {
    public static void main(String[] args) {
        SpringApplication.run(MongoApp.class, args);
    }
}
