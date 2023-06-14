package bz.kata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude= ErrorMvcAutoConfiguration.class)
public class TestMongo {
    public static void main(String[] args) {
        SpringApplication.run(TestMongo.class, args);
    }
}
