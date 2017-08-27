package com.linxin.coding.service;

import com.linxin.coding.domain.Item;

import java.util.List;

public interface CheckoutService {
    String printCheckout(List<Item> items);
}
