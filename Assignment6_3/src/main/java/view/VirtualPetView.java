package view;

import java.awt.Toolkit;

import controller.PetController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.awt.Dimension;

public class VirtualPetView extends Application {

    private PetController controller;
    private Image image = new Image("/beluga_flip.png", true);
    private Image image1 = new Image("/beluga.png", true);
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private Canvas canvas = new Canvas((double) dimension.width / 2, (double) dimension.height / 2);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    private Thread t;

    @Override
    public void start(Stage stage) {
        StackPane stackPane = new StackPane();
        stackPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        stackPane.setPrefSize((double) dimension.width / 2, (double) dimension.height / 2);

        canvas.heightProperty().bind(stackPane.heightProperty());
        canvas.widthProperty().bind(stackPane.widthProperty());

        Platform.runLater(() -> {
            graphicsContext.drawImage(image, 0, 0, 100, 100);
        });

        stackPane.getChildren().add(canvas);

        stackPane.setOnMouseMoved(event -> {
            if (t != null) {
                t.interrupt();
            }
            controller.onMouseMove(event);
        });

        stackPane.setOnMouseExited(event -> {
            if (t != null) {
                t.interrupt();
            }
        });

        Scene scene = new Scene(stackPane);

        stage.setOnCloseRequest(windowEvent -> {
            t.interrupt();
        });

        stage.setTitle("Virtual Pet");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() {
        controller = new PetController(this);
    }

    public void movePet(double x, double y) {
        final double[] previousX = {controller.getPetX()};
        final double[] previousY = {controller.getPetY()};

        final double length = Math.sqrt(Math.pow(previousX[0] - x, 2) + Math.pow(previousY[0] - y, 2));
        final double unitX = (x - previousX[0]) / length;
        final double unitY = (y - previousY[0]) / length;

        final boolean flip = x < previousX[0];

        t = new Thread(() -> {
            while (true) {
                if (y < previousY[0] && x < previousX[0]) {
                    if (controller.getPetY() <= y && controller.getPetX() <= x) {
                        break;
                    }
                } else if (y < previousY[0] && x > previousX[0]) {
                    if (controller.getPetY() <= y && controller.getPetX() >= x) {
                        break;
                    }
                } else if (y > previousY[0] && x < previousX[0]) {
                    if (controller.getPetY() >= y && controller.getPetX() <= x) {
                        break;
                    }
                } else if (y > previousY[0] && x > previousX[0]) {
                    if (controller.getPetY() >= y && controller.getPetX() >= x) {
                        break;
                    }
                } else if (y == previousY[0] || x == previousX[0]) {
                    break;
                }
                Platform.runLater(() -> {
                    controller.setPetLocation(controller.getPetX() + unitX, controller.getPetY() + unitY, flip);
                });
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    break;
                }

            }
        });
        t.start();
    }

    public void updatePetLocation(double x, double y, boolean flip) {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        graphicsContext.drawImage(flip ? image1 : image, x, y, 100, 100);
    }
}
