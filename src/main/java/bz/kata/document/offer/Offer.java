package bz.kata.document.offer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;

@Document
public class Offer {

    @Id
    private final OfferId offerId;
    private final Long uuid;
    @Indexed
    private final Long shopId;
    private BigDecimal price;

    public Offer(OfferId offerId, Long uuid, Long shopId, BigDecimal price) {
        this.offerId = offerId;
        this.uuid = uuid;
        this.shopId = shopId;
        this.price = price;
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
