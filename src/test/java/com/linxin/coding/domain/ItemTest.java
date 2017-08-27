package com.linxin.coding.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linxin.coding.offer.FixedDiscount;
import com.linxin.coding.offer.MultiBuyWithDiscountPrice;
import com.linxin.coding.offer.MultiBuyWithFreeItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class ItemTest {

    public static final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void before() throws IOException {
        // add jackson java 8 support
        mapper.findAndRegisterModules();
    }

    @Test
    public void createNewItemWithoutOffer_Test() throws JsonProcessingException {
        Item item = new Item("cheese", new BigDecimal(9.99).setScale(2, RoundingMode.DOWN), null);
        Assert.assertEquals("cheese", item.getName());
    }

    @Test
    public void createNewItemWithOffer_Test() throws JsonProcessingException {
        FixedDiscount discountOffer = new FixedDiscount(50);
        Item item = new Item("cheese", BigDecimal.valueOf(9.99), Optional.of(discountOffer));
        Assert.assertEquals("cheese", item.getName());
    }

    @Test
    public void createNewItemWithMultibuyOffer_Test() throws JsonProcessingException {
        MultiBuyWithDiscountPrice multiBuyWithDiscountPrice = new MultiBuyWithDiscountPrice(2, BigDecimal.valueOf(4));
        Item item = new Item("bread", new BigDecimal(2.99).setScale(2, RoundingMode.DOWN), Optional.of(multiBuyWithDiscountPrice));
        Assert.assertEquals(multiBuyWithDiscountPrice, item.getOffer().get());
    }

    @Test
    public void createNewItemWithCheapestFreeOffer_Test() throws JsonProcessingException {
        MultiBuyWithFreeItem multiBuyWithFreeItem = new MultiBuyWithFreeItem(3, 1);
        Item item = new Item("orange", new BigDecimal(1.99).setScale(2, RoundingMode.DOWN), Optional.of(multiBuyWithFreeItem));
        Assert.assertEquals(multiBuyWithFreeItem, item.getOffer().get());
    }


    @Test
    public void createItemsFromJson() throws IOException {
        String itemJson = "[ { \"name\": \"cheese\", \"price\": 9.99, \"offer\": { \"type\": \"FixedDiscount\", \"discountPercentage\": 50 } }, { \"name\": \"milk\", \"price\": 2.99, \"offer\": { \"type\": \"FixedDiscount\", \"discountPercentage\": 50 } } ]";
        List<Item> itemList = mapper.readValue(itemJson, new TypeReference<List<Item>>() {
        });
        Assert.assertTrue(itemList.size() > 1);
    }
}
