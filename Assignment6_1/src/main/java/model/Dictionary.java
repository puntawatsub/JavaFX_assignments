package model;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private Map<String, String> words;

    public Dictionary() {
        words = new HashMap<>();
    }

    /**
     * add a word to the dictionary
     * @param entry the word itself
     * @param definition definition of the word
     */
    public void add(String entry, String definition) {
        words.put(entry, definition);
        words.put(definition, entry);
    }

    public String[] search(String entry) {
        String definition = words.get(entry);
        if (definition != null) {
            return new String[]{entry, definition};
        }
        return null;
    }


}
