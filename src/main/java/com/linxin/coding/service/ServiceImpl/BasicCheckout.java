package com.linxin.coding.service.ServiceImpl;

import com.linxin.coding.domain.Checkout;
import com.linxin.coding.domain.Item;
import com.linxin.coding.exception.CheckoutProcessingException;
import com.linxin.coding.offer.ItemOffer;
import com.linxin.coding.service.CheckoutService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BasicCheckout implements CheckoutService {

    private static final Logger LOGGER = Logger.getLogger(BasicCheckout.class.getName());

    public static final String INVALID_ITEMS = "Invalid Items";
    public static final String newLine = System.getProperty("line.separator");

    @Override
    public String printCheckout(Checkout checkout) throws CheckoutProcessingException {
        if (checkout == null) {
            throw new CheckoutProcessingException(INVALID_ITEMS);
        }
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Checkout items: ")
                .append(newLine);

        // print items
        checkout.getItems().forEach(item ->
                stringBuilder.append(item.getName())
                        .append(" : £")
                        .append(item.getPrice())
                        .append(item.getOffer().isPresent()
                                && Optional.ofNullable(item.getDiscountedPrice()).isPresent() ?
                                " - offer applied: "
                                        + item.getOffer().get().getOfferDescription()
                                        + " -£"
                                        + (new BigDecimal(item.getPrice().doubleValue() - item.getDiscountedPrice().doubleValue())
                                        .setScale(2, RoundingMode.DOWN))
                                : "")
                        .append(newLine)
        );

        //print offers
        stringBuilder.append(newLine).append("Applied offers: ").append(newLine);
        checkout.getOffers().keySet().stream()
                .map(itemOffer -> itemOffer.getOfferDescription())
                .forEach(description -> {
                    stringBuilder.append(description).append(newLine);
                });


        // print total price
        stringBuilder.append(newLine).append("Total Price: £")
                .append(checkout.getTotalPrice())
                .append(newLine);

        String output = stringBuilder.toString();

        System.out.println(output);

        return output;
    }

    @Override
    public Optional<BigDecimal> getDiscountedTotalPrice(List<Item> items) throws CheckoutProcessingException {

        if (items == null || items.isEmpty()) {
            throw new CheckoutProcessingException(INVALID_ITEMS);
        }

        // Get items without offer
        List<Item> itemList = items.stream()
                .filter(item -> !item.getOffer().isPresent())
                .collect(Collectors.toList());

        // Processing item with offer using map
        Map<ItemOffer, List<Item>> itemMaps = items.stream()
                .filter(item -> item.getOffer().isPresent())
                .collect(Collectors.groupingBy(item -> item.getOffer().get()));

        // Add items with price including offer
        itemMaps.keySet().stream()
                .map(itemOffer -> itemOffer.applyOffer(itemMaps.get(itemOffer)))
                .forEach(itemsAfterDiscount ->
                        itemList.addAll(itemsAfterDiscount)
                );

        // get total price after discount
        return itemList.stream()
                .map(item -> Optional.ofNullable(item.getDiscountedPrice()).isPresent() ? item.getDiscountedPrice() : item.getPrice())
                .reduce((price1, price2) -> price1.add(price2));
    }

    @Override
    public Checkout getCheckout(List<Item> items) throws CheckoutProcessingException {

        if (items == null || items.isEmpty()) {
            throw new CheckoutProcessingException(INVALID_ITEMS);
        }

        // create new checkout
        Checkout checkout = new Checkout();
        // set items
        checkout.setItems(items);

        // set offer list
        checkout.setOffers(items.stream()
                .filter(item -> item.getOffer().isPresent())
                .collect(Collectors.groupingBy(item -> item.getOffer().get())));

        // set total price
        checkout.setTotalPrice(getDiscountedTotalPrice(items).orElse(BigDecimal.ZERO));

        return checkout;
    }

}
