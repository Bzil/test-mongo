package bz.kata.document.shop;

import java.util.List;

public class ShopService {
    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> findAll(String tenant) {
        return shopRepository.findShopsByShopId_TenantId(tenant);
    }
}
