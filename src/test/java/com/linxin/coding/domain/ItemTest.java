package com.linxin.coding.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linxin.coding.offer.FixedDiscount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

public class ItemTest {

    public static final String PRICE_FORMAT = "#.00";
    public static final DecimalFormat df = new DecimalFormat(PRICE_FORMAT);
    public static final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void before() throws IOException {
        // add jackson java 8 support
        mapper.findAndRegisterModules();
    }

    @Test
    public void createNewItemWithoutOffer_Test() throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final DecimalFormat df = new DecimalFormat(PRICE_FORMAT);

        Item item = new Item("cheese", new BigDecimal(9.99), null);

        String serializedItem = mapper.writeValueAsString(item);
        System.out.println(serializedItem);

        Assert.assertEquals("cheese", item.getName());
    }

    @Test
    public void createNewItemWithOffer_Test() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        FixedDiscount discountOffer = new FixedDiscount(50);

        Item item = new Item("cheese", new BigDecimal(9.99), Optional.of(discountOffer));

        String serializedItem = mapper.writeValueAsString(item);
        System.out.println(serializedItem);

        Assert.assertEquals("cheese", item.getName());
    }


    @Test
    public void createItemsFromJson() throws IOException {
        String itemJson = "[ { \"name\": \"cheese\", \"price\": 9.99, \"offer\": { \"type\": \"FixedDiscount\", \"discountPercentage\": 50 } }, { \"name\": \"milk\", \"price\": 2.99, \"offer\": { \"type\": \"FixedDiscount\", \"discountPercentage\": 50 } } ]";


        List<Item> itemList = mapper.readValue(itemJson, new TypeReference<List<Item>>() {
        });

        Assert.assertNotNull(itemList);
    }
}
