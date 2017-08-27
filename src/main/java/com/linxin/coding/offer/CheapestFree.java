package com.linxin.coding.offer;

import com.linxin.coding.domain.Item;
import lombok.Data;

import java.util.List;

@Data
public class CheapestFree implements ItemOffer{

    private int threshold;

    @Override
    public List<Item> applyOffer(List<Item> items) {
        items.stream().map(item -> item.getPrice())
                .sorted();

        return null;
    }

}
