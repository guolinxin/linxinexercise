package com.linxin.coding.offer;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MultiBuyWithDiscountPriceTest {

    @Test
    public void createMultiBuyOffer_Test() {
        MultiBuyWithDiscountPrice multiBuyWithDiscountPrice = new MultiBuyWithDiscountPrice(2, new BigDecimal(4.99).setScale(2, RoundingMode.DOWN));
        Assert.assertTrue(4.99 == multiBuyWithDiscountPrice.getMultiBuyPrice().doubleValue());
    }

}
