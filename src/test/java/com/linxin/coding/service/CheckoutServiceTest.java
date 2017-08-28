package com.linxin.coding.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linxin.coding.domain.Checkout;
import com.linxin.coding.domain.Item;
import com.linxin.coding.exception.CheckoutProcessingException;
import com.linxin.coding.service.ServiceImpl.BasicCheckout;
import com.linxin.coding.util.ResourceLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CheckoutServiceTest {

    public static final ObjectMapper mapper = new ObjectMapper();
    public static String itemsJson;

    @Before
    public void before() throws IOException {

        // add jackson java 8 support
        mapper.findAndRegisterModules();

        // load json from file
        ResourceLoader itemResourceLoader = new ResourceLoader("data/items.json");
        itemsJson = itemResourceLoader.getResourceAsString();
    }

    @Test
    public void getDiscountedTotalPrice_Test() throws IOException, CheckoutProcessingException {

        BasicCheckout checkoutService = new BasicCheckout();

        // items from json
        List<Item> items = mapper.readValue(itemsJson, new TypeReference<List<Item>>() {
        });

        Optional<BigDecimal> totalPrice = checkoutService.getDiscountedTotalPrice(items);

        Assert.assertTrue(totalPrice.isPresent());

    }

    @Test
    public void getCheckout_Test() throws IOException, CheckoutProcessingException {

        BasicCheckout checkoutService = new BasicCheckout();

        // items from json
        List<Item> items = mapper.readValue(itemsJson, new TypeReference<List<Item>>() {
        });

        Checkout checkout = checkoutService.getCheckout(items);

        Assert.assertNotNull(checkout);

    }


    @Test
    public void printCheckout_Test() throws IOException, CheckoutProcessingException {
        BasicCheckout checkoutService = new BasicCheckout();
        // items from json
        List<Item> items = mapper.readValue(itemsJson, new TypeReference<List<Item>>() {
        });

        Checkout checkout = checkoutService.getCheckout(items);
        checkoutService.printCheckout(checkout);
    }

    @Test
    public void printMultiBuyItemsCheckout_Test() throws IOException, CheckoutProcessingException {
        BasicCheckout checkoutService = new BasicCheckout();

        ResourceLoader multiBuyResourceLoader = new ResourceLoader("data/multiBuyItems.json");
        String multiBuyItemsJson = multiBuyResourceLoader.getResourceAsString();

        // items from json
        List<Item> items = mapper.readValue(multiBuyItemsJson, new TypeReference<List<Item>>() {
        });

        Checkout checkout = checkoutService.getCheckout(items);
        checkoutService.printCheckout(checkout);
    }


    @Test(expected = CheckoutProcessingException.class)
    public void checkoutException_Test() throws CheckoutProcessingException {
        BasicCheckout checkoutService = new BasicCheckout();
        checkoutService.getDiscountedTotalPrice(null);
    }
}
