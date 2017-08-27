package com.linxin.coding.offer;

import com.linxin.coding.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Class representing offer buy multi items cheapest free
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MultiBuyWithFreeItem implements ItemOffer {

    private int offerItemsThreshold; //5
    private int freeItemsThreshold; //3

    @Override
    public List<Item> applyOffer(List<Item> items) {
        List<Item> itemsList = items.stream()
                .sorted((item1, item2) -> Double.compare(item2.getPrice().doubleValue(), item1.getPrice().doubleValue()))
                .collect(toList());

        // set cheapest item price to 0
        for (int i = getOfferItemsThreshold() - 1; i < itemsList.size(); i += getOfferItemsThreshold()) {
            final int index = i;
            if (getFreeItemsThreshold() > 1) {
                IntStream.range(0, getFreeItemsThreshold())
                        .forEach(j -> {
                            itemsList.get(index - j).setDiscountedPrice(BigDecimal.ZERO);
                        });
            } else {
                itemsList.get(index).setDiscountedPrice(BigDecimal.ZERO);
            }

        }

        return itemsList;
    }

    @Override
    public String getOfferDescription() {
        return new StringBuilder().append("Buy ")
                .append(getOfferItemsThreshold())
                .append(" items get last ")
                .append(getFreeItemsThreshold())
                .append(" cheapest free")
                .toString();
    }

}
