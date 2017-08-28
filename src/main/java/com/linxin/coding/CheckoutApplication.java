package com.linxin.coding;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linxin.coding.domain.Checkout;
import com.linxin.coding.domain.Item;
import com.linxin.coding.exception.CheckoutProcessingException;
import com.linxin.coding.service.ServiceImpl.BasicCheckout;
import com.linxin.coding.util.ResourceLoader;

import java.io.IOException;
import java.util.List;

public class CheckoutApplication {

    public static void main(String[] args) {

        final String itemsTest = "data/items.json";
        final String multiBuyTest = "data/multiBuyItems.json";

        // load resources fies
        ResourceLoader resourceLoader = new ResourceLoader(itemsTest);

        final ObjectMapper mapper = new ObjectMapper();
        // add jackson java 8 support
        mapper.findAndRegisterModules();

        BasicCheckout checkoutService = new BasicCheckout();

        String itemsJson = null;

        try {
            itemsJson = resourceLoader.getResourceAsString();

            // items from json
            List<Item> items = mapper.readValue(itemsJson, new TypeReference<List<Item>>() {
            });

            Checkout checkout = checkoutService.getCheckout(items);
            checkoutService.printCheckout(checkout);

        } catch (IOException | CheckoutProcessingException e) {
            e.printStackTrace();
        }
    }
}
