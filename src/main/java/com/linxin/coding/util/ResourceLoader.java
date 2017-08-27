package com.linxin.coding.util;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;

/**
 * Utility class to load resource file
 */
public class ResourceLoader {
    private String filePath;

    public ResourceLoader(String filePath) {
        this.filePath = filePath;

        if (filePath.startsWith("/")) {
            throw new IllegalArgumentException("Relative paths may not have a leading slash!");
        }
    }

    public InputStream getResource() throws NoSuchFileException {
        ClassLoader classLoader = this.getClass().getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new NoSuchFileException("Resource file not found. Note that the current directory is the source folder!");
        }

        return inputStream;
    }

    public String getResourceAsString() throws IOException {
        InputStream inputStream = getResource();

        StringBuilder textBuilder = new StringBuilder();

        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }

        return textBuilder.toString();
    }
}