package com.mendeley.coding;

import com.mendeley.coding.ListReader;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListReaderTest {

    @Test
    public void shouldReadEmptyFile(){
        List<Item> items = new ListReader().read("src/test/resources/empty.json");
        assertThat(items).isEmpty();
    }

    @Test
    public void shouldReadSingleItemList(){
        List<Item> items = new ListReader().read("src/test/resources/single.json");
        assertThat(items).hasSize(1);
        Item item = items.get(0);
        assertThat(item.getName()).isEqualTo("beans");
        assertThat(item.getPrice()).isEqualTo(new BigDecimal("1.50" ));
    }

    @Test
    public void shouldReadMultipleItemList(){
        List<Item> items = new ListReader().read("src/test/resources/multiple.json");
        assertThat(items).hasSize(2);
    }

    @Test(expected = ListIngestException.class)
    public void shouldThrowErrorForMalformedInput(){
        List<Item> items = new ListReader().read("src/test/resources/malformed.json");
        assertThat(items).hasSize(1);
    }

    @Test(expected = ListIngestException.class)
    public void shouldThrowErrorIfFileDOesNotExist(){
        List<Item> items = new ListReader().read("nonexistent.json");
        assertThat(items).hasSize(1);
    }
}
