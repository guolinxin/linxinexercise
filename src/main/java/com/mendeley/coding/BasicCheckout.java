package com.mendeley.coding;

import java.math.BigDecimal;
import java.util.List;

public class BasicCheckout implements Checkout {


    @Override
    public String checkout(List<Item> items) {
        StringBuilder sb = new StringBuilder();
        items.stream().forEach(i -> {sb
                .append(i.getName())
                .append(" : £")
                .append(i.getPrice())
                .append("\n");});

        BigDecimal sum = items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        sb.append("total price : £").append(sum);
        String output = sb.toString();
        System.out.print(output);
        return output;
    }
}
