package bz.kata.document.shop;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends MongoRepository<Shop, Shop.ShopId> {

    List<Shop> findShopsByShopId_TenantId(String tenant);

    Optional<Shop> findByShopId_TenantIdAndName(String tenant, String name);

}
