package com.linxin.coding.offer;

import com.linxin.coding.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Class to represent item discount
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FixedDiscount implements ItemOffer {

    private int discountPercentage;

    @Override
    public List<Item> applyOffer(List<Item> items) {

        // apply discount, e.g.: 20% off -> 80% original price
        return items.stream()
                .map(item -> {
                    BigDecimal discountedPrice = new BigDecimal(item.getPrice()
                            .setScale(2, RoundingMode.DOWN)
                            .doubleValue() * (1 - (double) getDiscountPercentage() / 100));
                    item.setDiscountedPrice(discountedPrice.setScale(2, RoundingMode.DOWN));
                    return item;
                }).collect(toList());
    }

    @Override
    public String getOfferDescription() {
        return new StringBuilder().append(getDiscountPercentage())
                .append("% off")
                .toString();
    }

}
