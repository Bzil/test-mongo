package bz.kata.app.service;

import bz.kata.app.document.Offer;
import bz.kata.app.repository.OfferRepository;
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
        return offerRepository.findById(offerId);
    }

    public Offer create(long offerId) {
        return offerRepository.save(new Offer(offerId, new BigDecimal("22"), 2L));
    }
}
