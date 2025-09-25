package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Note;

public class NoteListController {
    @FXML
    public VBox listView;

    @FXML
    public Label listViewLabel;

    @FXML
    public Label listViewTitle;

    @FXML
    public ContextMenu contextMenu;

    public Note note;
}
