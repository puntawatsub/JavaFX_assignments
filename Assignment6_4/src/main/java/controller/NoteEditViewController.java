package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Note;
import model.Notebook;
import view.NoteListView;

import java.io.IOException;
import java.util.Arrays;

public class NoteEditViewController {
    @FXML
    private TextArea contentField;

    @FXML
    private TextField titleField;

    @FXML
    private Button editAddButton;

    private Alert alert;

    private boolean recentlyChanged = false;

    private Note note;

    @FXML
    private void onAdd() {
        note.setContent(contentField.getText());
        note.setTitle(titleField.getText());
        if (!Arrays.stream(Notebook.getInstance().getNotes()).toList().contains(note)) {
            Notebook.getInstance().addNote(note);
        }
        Notebook.getInstance().writeNotebook();
        recentlyChanged = false;
    }

    @FXML
    private void onTitleSelect() {
        titleField.selectAll();
    }

    public void start(Stage stage, Note note) {

        this.note = note;
        titleField.setText(note.getTitle());
        contentField.setText(note.getContent());

        titleField.focusedProperty().addListener((observable, oldVal, isFocused) -> {
            if (!isFocused) {
                if (titleField.getText().isBlank()) {
                    titleField.setText("Untitled");
                }
            }
        });

        titleField.textProperty().addListener(o -> {
            recentlyChanged = true;
        });

        contentField.textProperty().addListener(o -> {
            recentlyChanged = true;
        });

        stage.setOnCloseRequest(event -> {
            if (recentlyChanged) {
                event.consume();
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Are you sure?");
                alert.setContentText("Do you want to add current note?");
                alert.setHeaderText("Your current changes will not be saved");
                ButtonType add = new ButtonType("Add");
                ButtonType doNotAdd = new ButtonType("Do not add");
                alert.getButtonTypes().removeFirst();
                alert.getButtonTypes().addAll(add, doNotAdd, ButtonType.CANCEL);

                alert.showAndWait().ifPresent(response -> {
                    if (response == add) {
                        onAdd();
                        stage.close();
                        try {
                            new NoteListView().loadGUI();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (response == doNotAdd) {
                        stage.close();
                        try {
                            new NoteListView().loadGUI();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                try {
                    new NoteListView().loadGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
