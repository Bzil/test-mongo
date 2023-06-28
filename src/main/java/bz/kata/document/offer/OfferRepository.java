package bz.kata.document.offer;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends MongoRepository<Offer, Offer.OfferId> {
    List<Offer> findOffersByOfferId_TenantId(String tenantId);

    List<Offer> findOffersByOfferId_TenantIdAndShopName(String tenantId, String shopName);
}
