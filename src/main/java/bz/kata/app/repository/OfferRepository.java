package bz.kata.app.repository;

import bz.kata.app.document.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends MongoRepository<Offer, Long> {

    List<Offer> findById(long offerId);
}
