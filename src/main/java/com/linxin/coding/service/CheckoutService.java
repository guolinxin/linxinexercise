package com.linxin.coding.service;

import com.linxin.coding.domain.Checkout;
import com.linxin.coding.domain.Item;
import com.linxin.coding.exception.CheckoutProcessingException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CheckoutService {

    /**
     * Print total price of the items, a list of the items bought and any special offers applied
     *
     * @param checkout
     * @return
     */
    String printCheckout(Checkout checkout) throws CheckoutProcessingException;

    /**
     * Get items total price
     *
     * @param items
     * @return
     * @throws CheckoutProcessingException
     */
    Optional<BigDecimal> getDiscountedTotalPrice(List<Item> items) throws CheckoutProcessingException;

    /**
     * Get checkout for items
     *
     * @param items
     * @return
     * @throws CheckoutProcessingException
     */
    Checkout getCheckout(List<Item> items) throws CheckoutProcessingException;
}
