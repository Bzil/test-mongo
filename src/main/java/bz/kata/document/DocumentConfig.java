package bz.kata.document;

import bz.kata.document.offer.OfferRepository;
import bz.kata.document.offer.OfferService;
import bz.kata.document.shop.ShopRepository;
import bz.kata.document.shop.ShopService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "bz.kata.document")
public class DocumentConfig {
    @Bean
    public OfferService offerService(OfferRepository offerRepository, ShopRepository shopRepository) {
        return new OfferService(offerRepository, shopRepository);
    }

    @Bean
    public ShopService shopService(ShopRepository shopRepository) {
        return new ShopService(shopRepository);
    }
}
