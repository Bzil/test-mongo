package bz.kata.db.migration;


import bz.kata.document.offer.Offer;
import bz.kata.document.offer.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoJsonSchemaCreator;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.stereotype.Component;

import java.util.stream.LongStream;

@Component
public class OfferCreator {
    private static final Logger logger = LoggerFactory.getLogger(OfferCreator.class);
    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;
    private final OfferService offerService;

    public OfferCreator(
            MongoTemplate mongoTemplate,
            MongoOperations mongoOperations,
            OfferService offerService) {
        this.mongoTemplate = mongoTemplate;
        this.mongoOperations = mongoOperations;
        this.offerService = offerService;
    }

    public void init() {
        if (mongoTemplate.collectionExists(Offer.class)) {
            logger.error("Offer collection already exist - please delete it");
            return;
        }
        MongoJsonSchema offerSchema = MongoJsonSchemaCreator.create(mongoOperations.getConverter())
                .createSchemaFor(Offer.class);

        mongoTemplate.createCollection(Offer.class, CollectionOptions.empty().schema(offerSchema));
       String ensureIndex = mongoTemplate.indexOps(Offer.class).ensureIndex(new Index().named("idx_offer_last_updated_date").on("lastUpdatedDate", Sort.Direction.ASC));
        LongStream.range(1, 100)
                .forEach(offerId -> offerService.create(offerId % 2 == 0 ? "bzil-test" : "victor-test" , offerId));
    }

    public void cleanup() {
        if (mongoTemplate.collectionExists(Offer.class)) {
            mongoTemplate.dropCollection(Offer.class);
        }
    }
}
