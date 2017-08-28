package com.linxin.coding.offer;

import org.junit.Assert;
import org.junit.Test;

public class MultiBuyWithFreeItemTest {

    @Test
    public void createCheapestFreeOffer_Test() {
        MultiBuyWithFreeItem multiBuyWithFreeItem = new MultiBuyWithFreeItem(3, 1);
        Assert.assertEquals(3, multiBuyWithFreeItem.getOfferItemsThreshold());
    }

    @Test
    public void createFiveForThreeOffer() {
        MultiBuyWithFreeItem multiBuyWithFreeItem = new MultiBuyWithFreeItem(5, 2);
        Assert.assertEquals(2, multiBuyWithFreeItem.getFreeItemsThreshold());
    }
}
