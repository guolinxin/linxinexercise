package com.linxin.coding.offer;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.linxin.coding.domain.Item;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FixedDiscount.class, name = "FixedDiscount"),
        @JsonSubTypes.Type(value = CheapestFree.class, name = "CheapestFree"),
        @JsonSubTypes.Type(value = MultiBuy.class, name = "MultiBuy")
})
public interface ItemOffer {
    List<Item> applyOffer(List<Item> items);
}
