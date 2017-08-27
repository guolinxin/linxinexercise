package com.linxin.coding.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linxin.coding.offer.FixedDiscount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.DecimalFormat;

public class OfferTest {

    public static final String PRICE_FORMAT = "#.00";
    public static final DecimalFormat df = new DecimalFormat(PRICE_FORMAT);
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
        String discountItemJson = "{\"discountPercentage\":50,\"offerType\":\"FIXED_DISCOUNT\"}";
        FixedDiscount discount = mapper.readValue(discountItemJson, FixedDiscount.class);

        Assert.assertEquals(50, discount.getDiscountPercentage());

    }
}
