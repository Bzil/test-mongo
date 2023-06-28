package bz.kata.document;

import bz.kata.document.offer.OfferRepository;
import bz.kata.document.offer.OfferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "bz.kata.document.shop")
public class DocumentConfig {
    @Bean
    public OfferService offerService(OfferRepository offerRepository) {
        return new OfferService(offerRepository);
    }
}
