package bz.kata.document.offer;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Optional<Offer> findById(Long offerId) {
        if (offerId == null) {
            throw new RuntimeException("OfferId must not be null");
        }
        return offerRepository.findById(new Offer.OfferId("local", offerId));
    }

    public Offer create(Long offerId) {
        return offerRepository.save(new Offer(new Offer.OfferId("local", offerId), offerId, 2L, new BigDecimal("22")));
    }
}
