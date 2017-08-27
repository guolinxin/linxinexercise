package com.linxin.coding.offer;

import com.linxin.coding.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixedDiscount implements ItemOffer {

    private int discountPercentage;

    @Override
    public List<Item> applyOffer(List<Item> items) {

        return items.stream()
                .map(item -> {
                    BigDecimal discountedPrice = new BigDecimal(item.getPrice().doubleValue() * (discountPercentage / 100));
                    item.setPrice(discountedPrice);
                    return item;
                }).collect(toList());
    }

}
