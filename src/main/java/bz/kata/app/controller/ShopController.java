package bz.kata.app.controller;

import bz.kata.document.shop.Shop;
import bz.kata.document.shop.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping("{tenant}/shops")
    @ResponseBody
    public ResponseEntity<List<Shop>> getOfferById(@PathVariable("tenant") String tenant) {
        return ResponseEntity.ok(shopService.findAll(tenant));
    }
}
