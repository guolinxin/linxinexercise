package com.linxin.coding.service;

import com.linxin.coding.domain.Checkout;
import com.linxin.coding.domain.Item;
import com.linxin.coding.offer.ItemOffer;

import java.util.List;

public interface OfferService {
    List<ItemOffer> getOffers();

    Checkout applyOffer(List<Item> items);
}
