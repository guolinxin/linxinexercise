package com.linxin.coding.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linxin.coding.service.ServiceImpl.BasicCheckout;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.DecimalFormat;

public class CheckoutService {

    public static final String PRICE_FORMAT = "#.00";
    public static final DecimalFormat df = new DecimalFormat(PRICE_FORMAT);
    public static final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void before() throws IOException {
        // add jackson java 8 support
        mapper.findAndRegisterModules();
    }

    @Test
    public void testItemList(){
        String itemlist = "[ { \"name\": \"cheese\", \"price\": 9.99, \"offer\": { \"offerType\": \"FIXED_DISCOUNT\", \"discountPercentage\": 50 } }, { \"name\": \"milk\", \"price\": 2.99, \"offer\": { \"offerType\": \"FIXED_DISCOUNT\", \"discountPercentage\": 50 } } ]";



        BasicCheckout checkoutService = new BasicCheckout();
        //checkoutService.getDiscountedItems()

    }
}
