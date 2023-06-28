package bz.kata.document.offer;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Optional<Offer> findById(String tenant, Long offerId) {
        if (offerId == null) {
            throw new RuntimeException("OfferId must not be null");
        }
        return offerRepository.findById(new Offer.OfferId(tenant, offerId));
    }

    public Offer create(String tenant, Long offerId) {
        Instant now = Instant.now();
        Offer entity = new Offer(new Offer.OfferId(tenant, offerId), offerId, 2L, new BigDecimal("22"), now.minus(2L, ChronoUnit.HOURS), now);
        entity.setToto("test");
        return offerRepository.save(entity);
    }

    public List<Offer> findAll(String tenant) {
        return offerRepository.findOffersByOfferId_TenantId(tenant);
    }
}
