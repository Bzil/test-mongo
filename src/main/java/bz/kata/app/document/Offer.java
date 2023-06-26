package bz.kata.app.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;

@Document
public class Offer {

    @Id
    private Long id;
    private BigDecimal price;
    private Long shopId;

    public Offer(Long id, BigDecimal price, Long shopId) {
        this.id = id;
        this.price = price;
        this.shopId = shopId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
        return Objects.equals(id, offer.id) && Objects.equals(price, offer.price) && Objects.equals(shopId, offer.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, shopId);
    }
}
