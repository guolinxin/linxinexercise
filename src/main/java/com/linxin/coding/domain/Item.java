package com.linxin.coding.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linxin.coding.offer.ItemOffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String name;
    private BigDecimal price;

    @JsonIgnore
    private BigDecimal discountedPrice;

    @JsonInclude
    private Optional<ItemOffer> offer;

    @JsonCreator
    public Item(@JsonProperty("name") String name,
                @JsonProperty("price") BigDecimal price,
                @JsonProperty("offer") Optional<ItemOffer> offer) {

        this.name = name;
        this.price = price;
        this.offer = offer;
    }
}
