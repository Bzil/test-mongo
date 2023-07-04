package bz.kata.db.migration;


import bz.kata.document.offer.Offer;
import bz.kata.document.offer.OfferRepository;
import bz.kata.document.shop.Shop;
import bz.kata.document.shop.ShopRepository;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoJsonSchemaCreator;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class CollectionCreator {
    private static final Logger logger = LoggerFactory.getLogger(CollectionCreator.class);
    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;
    private final OfferRepository offerRepository;
    private final ShopRepository shopRepository;

    public CollectionCreator(MongoTemplate mongoTemplate, MongoOperations mongoOperations, OfferRepository offerRepository, ShopRepository shopRepository) {
        this.mongoTemplate = mongoTemplate;
        this.mongoOperations = mongoOperations;
        this.offerRepository = offerRepository;
        this.shopRepository = shopRepository;
    }

    public void init() {
        createCollection(Offer.class);
        createCollection(Shop.class);

        List<Shop> shops = List.of(
                createShop("bzil-test", "my-shop"),
                createShop("bzil-test", "your-shop"),
                createShop("victor-test", "my-shop"),
                createShop("victor-test", "your-shop")
        );

        List<Offer> offers = new ArrayList<>(5_000);
        for (long offerId = 0; offerId < 1_000_000; offerId++) {
            Shop shop = shops.get((int) (offerId % 4));
            offers.add(create(shop.getShopId().tenantId(), shop, offerId));

            if (offers.size() == 5_000) {
                offerRepository.saveAll(offers);
                offers.clear();
            }
        }
    }

    private void createCollection(Class<?> document) {
        if (!mongoTemplate.collectionExists(document)) {
            MongoJsonSchema offerSchema = MongoJsonSchemaCreator.create(mongoOperations.getConverter())
                    .createSchemaFor(document);

            mongoTemplate.createCollection(document, CollectionOptions.empty().schema(offerSchema));
        } else {
            logger.error("{} collection already exist - please delete it", document);
        }
    }

    public void cleanup() {
        dropCollection(Shop.class);
        dropCollection(Offer.class);
    }

    private void dropCollection(Class<?> document) {
        if (mongoTemplate.collectionExists(document)) {
            mongoTemplate.dropCollection(document);
        }
    }

    public Offer create(String tenant, Shop shop, Long offerId) {
        Instant now = Instant.now();
        return new Offer(new Offer.OfferId(tenant, offerId), offerId, shop, new BigDecimal("22"), now.minus(1L, ChronoUnit.DAYS), now);
    }

    private Shop createShop(String tenant, String shopName) {
        Instant now = Instant.now();
        long id = RandomUtils.nextLong();
        return shopRepository.save(new Shop(new Shop.ShopId(tenant, id, id + 1L), shopName, now.minus(3L, ChronoUnit.DAYS), now));
    }
}
