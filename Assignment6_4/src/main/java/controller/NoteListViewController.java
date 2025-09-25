package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Note;
import model.Notebook;
import view.NoteEditView;

import java.awt.Dimension;
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
//        for (int i = 0; i < 10; i++) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reusables/NoteList.fxml"));
//            Parent listViewNode = loader.load();
//            NoteListController noteListController = loader.getController();
//
//            noteListController.listViewLabel.setText("Banana");
//            noteListController.listViewTitle.setText("Banana");
//
//            flowPane.getChildren().add(listViewNode);
//        }

        notebook = Notebook.getInstance();

        notebook.loadNotebook();

        Note[] notes = notebook.getNotes();
        for (Note note: notes) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reusables/NoteList.fxml"));
                Parent listViewNode = loader.load();
                NoteListController noteListController = loader.getController();

                noteListController.listViewLabel.setText(note.getContent());
                noteListController.listViewTitle.setText(note.getTitle());

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
            noteEditView = new NoteEditView(note);
            try {
                noteEditView.startGUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.close();
        });
    }

    public Notebook getNotebook() {
        return notebook;
    }
}
