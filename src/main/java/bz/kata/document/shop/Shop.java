package bz.kata.document.shop;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "shops")
public class Shop {

    @Id
    private final ShopId shopId;
    private final String name;
    private final Instant creationDate;
    private final Instant lastUpdatedDate;

    public Shop(ShopId shopId, String name, Instant creationDate, Instant lastUpdatedDate) {
        this.shopId = shopId;
        this.name = name;
        this.creationDate = creationDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public record ShopId(String tenantId, Long shopId, Long shopUuid){}

    public ShopId getShopId() {
        return shopId;
    }

    public String getName() {
        return name;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }
}
