package com.linxin.coding.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linxin.coding.offer.FixedDiscount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class OfferTest {

    public static final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void before() throws IOException {
        // add jackson java 8 support
        mapper.findAndRegisterModules();
    }

    @Test
    public void createNewDiscountOffer_Test() throws JsonProcessingException {
        FixedDiscount discountOffer = new FixedDiscount(50);
        Assert.assertEquals(50, discountOffer.getDiscountPercentage());
    }

    @Test
    public void createNewDiscountOfferFromJson_Test() throws IOException {
        final String discountItemJson = "{ \"type\": \"FixedDiscount\", \"discountPercentage\": 50 }";
        FixedDiscount discount = mapper.readValue(discountItemJson, FixedDiscount.class);

        Assert.assertEquals(50, discount.getDiscountPercentage());

    }
}
