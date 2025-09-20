package controller;

import javafx.scene.control.Label;
import model.Dictionary;
import view.DictionaryView;

import java.util.Objects;

public class DictionaryController {

    private Dictionary dictionary;
    private DictionaryView view;

    public DictionaryController(DictionaryView view) {
        dictionary = new Dictionary();
        this.view = view;
        dictionary.add("aakkonen", "alphabet");
        dictionary.add("kirja", "book");
        dictionary.add("talo", "house");
        dictionary.add("puu", "tree");
        dictionary.add("koira", "dog");
        dictionary.add("kissa", "cat");
        dictionary.add("auto", "car");
        dictionary.add("vesi", "water");
        dictionary.add("valo", "light");
        dictionary.add("yö", "night");
        dictionary.add("päivä", "day");
        dictionary.add("onni", "happiness");
        dictionary.add("rakkaus", "love");
        dictionary.add("ystävä", "friend");
        dictionary.add("koulu", "school");
        dictionary.add("kauppa", "shop");
        dictionary.add("ruoka", "food");
        dictionary.add("leipä", "bread");
        dictionary.add("omena", "apple");
        dictionary.add("meri", "sea");
        dictionary.add("taivas", "sky");
    }

    public void searchButtonPressed(String entry) {
        String[] result = dictionary.search(entry);
        if (result != null) {
            view.updateResult(result[1]);
            view.emptySearchField();
        } else {
            view.updateResult("Result Not Found");
        }
    }

}
