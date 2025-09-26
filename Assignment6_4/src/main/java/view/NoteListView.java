package view;

import controller.NoteAsListViewController;
import controller.NoteListViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NoteListView extends Application {

    private NoteListViewController controller;
    private NoteAsListViewController controller1;

    private boolean isList = false;

    @Override
    public void start(Stage stage) {
        try {
            loadGUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGUI() throws IOException {

        if (!isList) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/NoteListView.fxml"));
            Parent root = fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.start(stage);

            Scene scene = new Scene(root);

            stage.setTitle("Notes");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } else {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/NoteListViewAsList.fxml"));

            Parent root = fxmlLoader.load();

            controller1 = fxmlLoader.getController();
            controller1.start(stage);

            Scene scene = new Scene(root);

            stage.setTitle("Notes");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void setList(boolean list) throws IOException {
        isList = list;
        loadGUI();
    }
}
