package com.linxin.coding.offer;

import org.junit.Assert;
import org.junit.Test;

public class CheapesFreeTest {

    @Test
    public void createCheapestFreeOffer_Test() {
        MultiBuyWithFreeItem multiBuyWithFreeItem = new MultiBuyWithFreeItem(5, 2);
        Assert.assertEquals(5, multiBuyWithFreeItem.getOfferItemsThreshold());
    }
}
