package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Note;
import model.Notebook;

import java.util.Arrays;

public class NoteEditViewController {
    @FXML
    private TextArea contentField;

    @FXML
    private TextField titleField;

    @FXML
    private Button editAddButton;

    public void start(Stage stage, Note note) {

        titleField.setText(note.getTitle());
        contentField.setText(note.getContent());

        titleField.focusedProperty().addListener((observable, oldVal, isFocused) -> {
            if (!isFocused) {
                if (titleField.getText().isBlank()) {
                    titleField.setText("Untitled");
                }
            }
        });

        editAddButton.setOnAction(event -> {
            note.setContent(contentField.getText());
            note.setTitle(titleField.getText());
            if (!Arrays.stream(Notebook.getInstance().getNotes()).toList().contains(note)) {
                Notebook.getInstance().addNote(note);
            }
            Notebook.getInstance().writeNotebook();
        });
    }
}
