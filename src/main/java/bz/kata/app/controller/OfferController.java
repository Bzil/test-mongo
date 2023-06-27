package bz.kata.app.controller;

import bz.kata.document.offer.Offer;
import bz.kata.document.offer.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping("/offer/{id}")
    @ResponseBody
    public ResponseEntity<Offer> getOfferById(@PathVariable("id") long offerId) {
        return ResponseEntity.of(offerService.findById(offerId));
    }


    @RequestMapping("/create/{id}")
    @ResponseBody
    public ResponseEntity<Offer> create(@PathVariable("id") long offerId) {
        return ResponseEntity.ok(offerService.create(offerId));
    }


}
