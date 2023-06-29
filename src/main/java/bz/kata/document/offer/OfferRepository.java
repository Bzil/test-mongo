package bz.kata.document.offer;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface OfferRepository extends MongoRepository<Offer, Offer.OfferId> {
    List<Offer> findOffersByOfferId_TenantId(String tenantId);

    List<Offer> findOffersByOfferId_TenantIdAndShopName(String tenantId, String shopName);

    @Query(value = "{ 'offerId.tenantId': ?0, $or: [ { 'creationDate' :{$gte: ?1 ,$lt: ?2} }, { 'lastUpdatedDate' :{$gte: ?1 ,$lt: ?2} } ] }")
    List<Offer> findOffersUpdatedBetween(String tenant, Instant lower, Instant upper);
}
