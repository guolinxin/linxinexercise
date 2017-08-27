package com.linxin.coding.offer;

import com.linxin.coding.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiBuy implements ItemOffer {

    private int threshold;
    private BigDecimal multiByPrice;

    @Override
    public List<Item> applyOffer(List<Item> items) {
        return null;
    }
}
