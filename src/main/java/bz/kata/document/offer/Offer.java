package bz.kata.document.offer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Document(collection = "offers")
public class Offer {

    @Id
    private final OfferId offerId;
    private final Long uuid;
    private final Long shopId;
    private final BigDecimal price;

    @Indexed(name = "idx_offer_creation_date")
    private final Instant creationDate;
    @Indexed(name = "idx_offer_last_updated_date")
    private final Instant lastUpdatedDate;

    public Offer(OfferId offerId, Long uuid, Long shopId, BigDecimal price, Instant creationDate, Instant lastUpdatedDate) {
        this.offerId = offerId;
        this.uuid = uuid;
        this.shopId = shopId;
        this.price = price;
        this.creationDate = creationDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public OfferId getOfferId() {
        return offerId;
    }

    public Long getUuid() {
        return uuid;
    }

    public Long getShopId() {
        return shopId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Offer offer = (Offer) o;
        return Objects.equals(offerId, offer.offerId) && Objects.equals(uuid, offer.uuid) && Objects.equals(shopId, offer.shopId) && Objects.equals(price, offer.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId, uuid, shopId, price);
    }

    public record OfferId(String tenantId, Long offerId) {
    }

}
