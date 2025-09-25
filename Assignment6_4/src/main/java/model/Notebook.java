package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Notebook implements Serializable {
    private List<Note> notes;
    public static Notebook instance;

    final String FILE_LOCATION = "note.ser";

    private Notebook() {
        notes = new ArrayList<>();
    }

    public static Notebook getInstance() {
        if (instance == null) {
            instance = new Notebook();
        }
        return instance;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(Note note) {
        notes.remove(note);
        writeNotebook();
    }

    public Note[] getNotes() {
        return notes.toArray(new Note[0]);
    }

    public void writeNotebook() {
        try (FileOutputStream fos = new FileOutputStream(FILE_LOCATION);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadNotebook() {
        try (FileInputStream fis = new FileInputStream(FILE_LOCATION);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
            Notebook notebook = (Notebook) ois.readObject();
            this.notes = new ArrayList<>(List.of(notebook.getNotes()));
        } catch (FileNotFoundException e) {} catch (IOException e) {} catch (ClassNotFoundException e) {}
    }
}
