package com.mendeley.coding;

import java.util.List;

public class CheckoutApplication {

    public static void main(String[] args){

        if(args.length<1){
            throw new RuntimeException("no path to input list provided");
        }
        String filePath = args[0];
        List<Item> itemList = new ListReader().read(filePath);
        Checkout basicCheckout = new BasicCheckout();
        String output = basicCheckout.checkout(itemList);
    }
}
