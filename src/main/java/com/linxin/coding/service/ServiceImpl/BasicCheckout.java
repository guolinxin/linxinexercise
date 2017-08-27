package com.linxin.coding.service.ServiceImpl;

import com.linxin.coding.domain.Item;
import com.linxin.coding.service.CheckoutService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BasicCheckout implements CheckoutService {

    @Override
    public String printCheckout(List<Item> items) {
        StringBuilder stringBuilder = new StringBuilder();

        items.forEach(item -> stringBuilder
                .append(item.getName())
                .append(" : £")
                .append(item.getPrice())
                .append("\n"));

        BigDecimal sum = items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        stringBuilder.append("total price : £").append(sum);

        String output = stringBuilder.toString();

        System.out.print(output);

        return output;
    }

    public List<Item> getDiscountedItems(List<Item> items){

        Map itemMaps = items.stream()
                .collect(Collectors.groupingBy(item -> item.getOffer()));

        return null;
    }

}
