package com.mendeley.coding;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListReader {
    ObjectMapper objectMapper = new ObjectMapper();

    public List<Item> read(String fileName) {
        try {
            String s = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
            List<Item> itemList = objectMapper.<List<Item>>readValue(s, new TypeReference<List<Item>>() {
            });
            return itemList;

        } catch (java.io.IOException e) {
           throw new ListIngestException(e);
        }
    }
}
