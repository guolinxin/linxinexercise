package com.linxin.coding.domain;

import com.linxin.coding.offer.ItemOffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checkout {

    @NonNull
    private List<Item> items;

    @NonNull
    private Map<ItemOffer, List<Item>> offers;

    @NonNull
    private BigDecimal totalPrice;
}
