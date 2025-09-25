package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Note;
import model.Notebook;

import java.awt.Dimension;
import java.io.IOException;

public class NoteListViewController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private FlowPane flowPane;

    private Notebook notebook;



    public void start() {
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

        notebook = new Notebook();

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
    }

    public Notebook getNotebook() {
        return notebook;
    }
}
