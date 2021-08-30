package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ExtractJson {
    public static void main(String[] args) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        customerDetailsExtractJson c = obj.readValue(new File("/Users/lavinaagrawal/TechProjects/JsonJava/src/main/java/org/example/customerInfoExtract.json"),
                customerDetailsExtractJson.class);
        System.out.println(c.getAmount());

    }
}
