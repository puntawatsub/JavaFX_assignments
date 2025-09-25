package view;

import controller.NoteListViewController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NoteListView extends Application {

    private NoteListViewController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/NoteListView.fxml"));
        Parent root = fxmlLoader.load();

        controller = fxmlLoader.getController();
        controller.start();

        Scene scene = new Scene(root);

        stage.setTitle("Notes");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
