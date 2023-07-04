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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final ShopRepository shopRepository;

    public OfferService(OfferRepository offerRepository, ShopRepository shopRepository) {
        this.offerRepository = offerRepository;
        this.shopRepository = shopRepository;
    }

    public Optional<Offer> findByOfferId(String tenant, Long offerId) {
        if (offerId == null) {
            throw new RuntimeException("OfferId must not be null");
        }
        return offerRepository.findByOfferId(new Offer.OfferId(tenant, offerId));
    }

    public List<Offer> findAll(String tenant) {
        return offerRepository.findOffersByOfferId_TenantId(tenant);
    }

    public List<Offer> findAllForShop(String tenant, String shopName) {
        return offerRepository.findOffersByOfferId_TenantIdAndShopName(tenant, shopName);
    }

    public Set<Offer> findOffersUpdatedBetween(String tenant, Instant lower, Instant upper) {
        Set<Shop.ShopId> shopsUpdated = shopRepository.findShopsUpdated(tenant, lower)
                .stream()
                .map(Shop::getShopId)
                .collect(Collectors.toSet());

        return offerRepository.findOffersUpdatedBetween(tenant, lower, upper, shopsUpdated);
    }
}
