package controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Note;
import model.Notebook;
import view.NoteEditView;

import java.io.IOException;

public class NoteAsListViewController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button addButton;

    private Notebook notebook;

    private NoteEditViewController noteEditViewController;

    private NoteEditView noteEditView;

    @FXML
    private ListView<String> listView;


    public void start(Stage stage) {
        notebook = Notebook.getInstance();

        notebook.loadNotebook();

        Note[] notes = notebook.getNotes();
        ContextMenu contextMenu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(deleteEvent -> {
            ObservableList<Integer> selectedIndices =
                    listView.getSelectionModel().getSelectedIndices();
            Notebook.getInstance().removeNote(notes[selectedIndices.get(0)]);
            Platform.runLater(() -> {
                listView.getItems().remove(listView.getItems().get(selectedIndices.get(0)));
            });
        });
        contextMenu.getItems().add(delete);
        listView.setContextMenu(contextMenu);

        listView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                ObservableList<Integer> selectedIndices =
                        listView.getSelectionModel().getSelectedIndices();
                startEditGUI(notes[selectedIndices.get(0)]);
                stage.close();
            }
        });
        for (Note note: notes) {
            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reusables/NoteList.fxml"));
//                Parent listViewNode = loader.load();
//                NoteListReusableController noteListReusableController = loader.getController();
//
//                noteListReusableController.listViewLabel.setText(note.getContent());
//                noteListReusableController.listViewTitle.setText(note.getTitle());
                listView.getItems().add(note.getTitle());



//                noteListReusableController.listViewLabel.setContextMenu(contextMenu);
//
//                listViewNode.setOnMouseClicked(event -> {
//                    if (event.getButton() == MouseButton.PRIMARY) {
//                        startEditGUI(note);
//                        stage.close();
//                    }
//                });

//                flowPane.getChildren().add(listViewNode);
            } catch (Exception e) {
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
