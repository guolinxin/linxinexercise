package com.linxin.coding;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linxin.coding.domain.Item;
import com.linxin.coding.service.CheckoutService;
import com.linxin.coding.service.ServiceImpl.BasicCheckout;
import com.linxin.coding.util.ResourceLoader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CheckoutApplication {

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new RuntimeException("No path to the input list was provided.");
        }

        String jsonString = "";
        List<Item> itemList = Collections.emptyList();

        ResourceLoader resourceLoader = new ResourceLoader("src/main/resources/data/items.json");

        try {
            jsonString = resourceLoader.getResourceAsString();

            ObjectMapper mapper = new ObjectMapper();
            itemList = mapper.readValue(jsonString, new TypeReference<List<Item>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        CheckoutService basicCheckout = new BasicCheckout();

        String output = basicCheckout.printCheckout(itemList);
    }
}
