package com.linxin.coding.offer;

import com.linxin.coding.domain.Item;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * Class representing offer multi items for discount price
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MultiBuyWithDiscountPrice implements ItemOffer {

    private int offerItemsThreshold;

    @NonNull
    private BigDecimal multiBuyPrice;

    @Override
    public List<Item> applyOffer(List<Item> items) {

        // Average price of item with multi-buy offer
        BigDecimal discountedPrice = this.getMultiBuyPrice()
                .divide(BigDecimal.valueOf(getOfferItemsThreshold()))
                .setScale(2, RoundingMode.DOWN);

        return items.stream()
                .map(item -> {
                    item.setDiscountedPrice(discountedPrice);
                    return item;
                })
                .collect(toList());

    }

    @Override
    public String getOfferDescription() {
        return new StringBuilder().append("Buy ")
                .append(getOfferItemsThreshold())
                .append(" for £")
                .append(this.getMultiBuyPrice())
                .toString();
    }
}
