package bz.kata.document.offer;

import bz.kata.document.shop.Shop;
import bz.kata.document.shop.ShopRepository;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final ShopRepository shopRepository;

    public OfferService(OfferRepository offerRepository, ShopRepository shopRepository) {
        this.offerRepository = offerRepository;
        this.shopRepository = shopRepository;
    }

    public Optional<Offer> findById(String tenant, Long offerId) {
        if (offerId == null) {
            throw new RuntimeException("OfferId must not be null");
        }
        return offerRepository.findById(new Offer.OfferId(tenant, offerId));
    }

    public Offer create(String tenant, String shopName, Long offerId) {
        Instant now = Instant.now();
        Shop shop = shopRepository.findByShopId_TenantIdAndName(tenant, shopName)
                .orElseGet(() -> {
                    long id = RandomUtils.nextLong();
                    return shopRepository.save(new Shop(new Shop.ShopId(tenant, id, id + 1L), shopName, now.minus(3L, ChronoUnit.DAYS), now));
                });

        Offer entity = new Offer(new Offer.OfferId(tenant, offerId), offerId, shop, new BigDecimal("22"), now.minus(2L, ChronoUnit.HOURS), now);
        return offerRepository.save(entity);
    }

    public List<Offer> findAll(String tenant) {
        return offerRepository.findOffersByOfferId_TenantId(tenant);
    }

    public List<Offer> findAllForShop(String tenant, String shopName) {
        return offerRepository.findOffersByOfferId_TenantIdAndShopName(tenant, shopName);
    }

    public List<Offer> findOffersUpdatedBetween(String tenant, Instant lower, Instant upper) {
        return offerRepository.findOffersUpdatedBetween(tenant, lower, upper);
    }
}
