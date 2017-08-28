package com.linxin.coding.offer;

import org.junit.Assert;
import org.junit.Test;

public class FixedDiscountTest {

    @Test
    public void createFixedDiscountOffer_Test() {
        FixedDiscount fixedDiscount = new FixedDiscount(30);
        Assert.assertNotNull(fixedDiscount);
    }

    @Test
    public void createFixedDiscountWithZero_Test() {
        FixedDiscount fixedDiscount = new FixedDiscount(0);
        Assert.assertNotNull(fixedDiscount);
    }
}
