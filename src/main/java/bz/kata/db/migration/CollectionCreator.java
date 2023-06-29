package bz.kata.db.migration;


import bz.kata.document.offer.Offer;
import bz.kata.document.offer.OfferService;
import bz.kata.document.shop.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoJsonSchemaCreator;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.stereotype.Component;

import java.util.stream.LongStream;

@Component
public class CollectionCreator {
    private static final Logger logger = LoggerFactory.getLogger(CollectionCreator.class);
    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;
    private final OfferService offerService;

    public CollectionCreator(
            MongoTemplate mongoTemplate,
            MongoOperations mongoOperations,
            OfferService offerService) {
        this.mongoTemplate = mongoTemplate;
        this.mongoOperations = mongoOperations;
        this.offerService = offerService;
    }

    public void init() {
        createCollection(Offer.class);
        createCollection(Shop.class);

        LongStream.range(1, 100)
                .forEach(offerId -> offerService.create(offerId % 2 == 0 ? "bzil-test" : "victor-test", offerId % 3 == 0 ? "bzil-shop" : "victor-shop" , offerId));
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
}
