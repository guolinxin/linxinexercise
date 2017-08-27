package com.linxin.coding.offer;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.linxin.coding.domain.Item;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FixedDiscount.class, name = "FixedDiscount"),
        @JsonSubTypes.Type(value = MultiBuyWithFreeItem.class, name = "MultiBuyWithFreeItem"),
        @JsonSubTypes.Type(value = MultiBuyWithDiscountPrice.class, name = "MultiBuyWithDiscountPrice")
})
public interface ItemOffer {

    /**
     * Apply offer to item and update price
     *
     * @param items
     * @return
     */
    List<Item> applyOffer(List<Item> items);

    /**
     * Get offer string description
     *
     * @return
     */
    String getOfferDescription();
}
