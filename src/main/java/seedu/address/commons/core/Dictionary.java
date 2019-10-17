package seedu.address.commons.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
    private String name;
    private final Map<String, Integer> dictionary;

    public Dictionary(String name, Map<String, Integer> dictionary) {
        this.name = name;
        this.dictionary = dictionary;
    }

    public static Dictionary build(String name) {
        switch (name) {
            case "passwords.txt" :
            return new Dictionary(name, load("/dictionaries/passwords.txt")); //TODO Ranked vs unranked?
        }
        return null; // TODO : no nulls.
    }

    private static Map<String, Integer> load(String path) {
        //File initialFile = new File(path);
        Map<String, Integer> dictionary = new HashMap<>();
        try (InputStream is = Dictionary.class.getResourceAsStream(path);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8")))
        {
            String line;
            int i = 1;
            while ((line = br.readLine()) != null)
            {
                dictionary.put(line, i++);
            }
        }
        catch (IOException e) {
            System.out.println("Error while reading " + path);
        }

        return dictionary;
    }


    public Map<String, Integer> getDictionary() {
        return this.dictionary;
    }
}
