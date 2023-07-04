package bz.kata.document.offer;


import bz.kata.document.shop.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OfferRepository extends MongoRepository<Offer, Offer.OfferId> {

    Optional<Offer> findByOfferId(Offer.OfferId offerId);
    List<Offer> findOffersByOfferId_TenantId(String tenantId);

    List<Offer> findOffersByOfferId_TenantIdAndShopName(String tenantId, String shopName);

    // Not working Invalid path reference shop.creationDate; Associations can only be pointed to directly or via their id property
    // @Query(value = "{ 'offerId.tenantId': ?0, $or: [ { 'creationDate' :{$gte: ?1 ,$lt: ?2} }, { 'lastUpdatedDate' :{$gte: ?1 ,$lt: ?2} }, { 'shop.creationDate' :{$gte: ?1 ,$lt: ?2} }, { 'shop.lastUpdatedDate' :{$gte: ?1 ,$lt: ?2} } ] }")
    @Query(value = """
                {
                  'offerId.tenantId': :#{#tenant},
                  $or: [
                    { 'creationDate' : { $gte: :#{#lower}, $lt: :#{#upper} } },
                    { 'lastUpdatedDate' : { $gte: :#{#lower}, $lt: :#{#upper} } },
                    { 'shopId' : { $in: :#{#shopIds} } }
                  ]
                }
            """)
    Set<Offer> findOffersUpdatedBetween(@Param("tenant") String tenant, @Param("lower") Instant lower, @Param("upper") Instant upper, @Param("shopIds") Set<Shop.ShopId> shopsUpdated);
}
