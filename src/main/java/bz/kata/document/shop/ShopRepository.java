package bz.kata.document.shop;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends MongoRepository<Shop, Shop.ShopId> {

    Optional<Shop> findByName(String name);

}
