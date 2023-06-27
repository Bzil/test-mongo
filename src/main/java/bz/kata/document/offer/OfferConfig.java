package bz.kata.document.offer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "bz.kata.document")
public class OfferConfig {
    @Bean
    public OfferService offerService(OfferRepository offerRepository) {
        return new OfferService(offerRepository);
    }
}
