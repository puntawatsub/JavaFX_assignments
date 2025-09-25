package view;

import controller.NoteEditViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Note;

import java.io.IOException;

public class NoteEditView {

    private Note note;

    private NoteEditViewController controller;

    public NoteEditView(Note note) {
        this.note = note;
    }

    public void startGUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NoteEditView.fxml"));
        Parent root = loader.load();

        controller = loader.getController();

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        controller.start(stage, this.note);
        stage.setScene(scene);
        stage.show();
    }
}
