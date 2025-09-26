package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Note;
import model.Notebook;
import view.NoteEditView;

import java.io.IOException;

public class NoteListViewController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private FlowPane flowPane;

    private Notebook notebook;

    @FXML
    private Button addButton;

    private NoteEditViewController noteEditViewController;

    private NoteEditView noteEditView;


    public void start(Stage stage) {
        notebook = Notebook.getInstance();

        notebook.loadNotebook();

        Note[] notes = notebook.getNotes();
        for (Note note: notes) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reusables/NoteList.fxml"));
                Parent listViewNode = loader.load();
                NoteListReusableController noteListReusableController = loader.getController();

                noteListReusableController.listViewLabel.setText(note.getContent());
                noteListReusableController.listViewTitle.setText(note.getTitle());

                ContextMenu contextMenu = new ContextMenu();
                MenuItem delete = new MenuItem("Delete");
                delete.setOnAction(event -> {
                    Notebook.getInstance().removeNote(note);
                    Platform.runLater(() -> {
                        flowPane.getChildren().remove(listViewNode);
                    });
                });
                contextMenu.getItems().add(delete);
                noteListReusableController.listViewLabel.setContextMenu(contextMenu);

                listViewNode.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        startEditGUI(note);
                        stage.close();
                    }
                });

                flowPane.getChildren().add(listViewNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        anchorPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        anchorPane.setPrefSize(610, 600);

        addButton.setOnAction(event -> {
            Note note = new Note();
            note.setTitle("Untitled");
            note.setContent("");
            startEditGUI(note);
            stage.close();
        });
    }

    public Notebook getNotebook() {
        return notebook;
    }

    private void startEditGUI(Note note) {
        noteEditView = new NoteEditView(note);
        try {
            noteEditView.startGUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
