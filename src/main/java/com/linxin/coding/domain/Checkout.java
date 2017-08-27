package com.linxin.coding.domain;

import com.linxin.coding.offer.ItemOffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checkout {

    private List<Item> items;
    private List<ItemOffer> offers;
    private BigDecimal totalPrice;

}
