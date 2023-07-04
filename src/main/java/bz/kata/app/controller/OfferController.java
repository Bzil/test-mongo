package bz.kata.app.controller;

import bz.kata.document.offer.Offer;
import bz.kata.document.offer.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

@RestController
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping("{tenant}/offer/{id}")
    @ResponseBody
    public ResponseEntity<Offer> getOfferById(@PathVariable("tenant") String tenant,
                                              @PathVariable("id") long offerId) {
        return ResponseEntity.of(offerService.findById(tenant, offerId));
    }

    @RequestMapping("{tenant}/offers")
    @ResponseBody
    public ResponseEntity<List<Offer>> findAll(@PathVariable("tenant") String tenant) {
        return ResponseEntity.ok(offerService.findAll(tenant));
    }

    @RequestMapping("{tenant}/{shop}/offers")
    @ResponseBody
    public ResponseEntity<List<Offer>> findAll(@PathVariable("tenant") String tenant,
                                               @PathVariable("shop") String shopName) {
        return ResponseEntity.ok(offerService.findAllForShop(tenant, shopName));
    }

    @RequestMapping("{tenant}/offers/updated")
    @ResponseBody
    public ResponseEntity<Set<Offer>> findAllUpdated(@PathVariable("tenant") String tenant) {
        Instant now = Instant.now();
        return ResponseEntity.ok(offerService.findOffersUpdatedBetween(tenant, Instant.now().minus(1, ChronoUnit.HOURS), now));
    }

    @RequestMapping("{tenant}/offers/shop/updated")
    @ResponseBody
    public ResponseEntity<Set<Offer>> findAllUpdatedShop(@PathVariable("tenant") String tenant) {
        Instant now = Instant.now();
        return ResponseEntity.ok(offerService.findOffersUpdatedBetween(tenant, Instant.now().minus(2, ChronoUnit.DAYS), Instant.now().minus(1, ChronoUnit.DAYS)));
    }

}
