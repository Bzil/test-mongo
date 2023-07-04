package bz.kata.db.migration;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class CollectionDescriptor {
    private static final Logger logger = LoggerFactory.getLogger(CollectionDescriptor.class);


    private final MongoOperations mongoOperations;

    public CollectionDescriptor(
            MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public void describeIndexes() {
        listIndexes("offers");
        listIndexes("shops");
    }

    private void listIndexes(String collectionName) {
        MongoCollection<Document> collection = mongoOperations.getCollection(collectionName);

        for (Document index : collection.listIndexes()) {
            logger.info("Index {}",  index.toJson());
        }
    }

}
