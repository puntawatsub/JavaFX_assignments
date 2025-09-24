package controller;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import model.Pet;
import view.VirtualPetView;

public class PetController {
    private VirtualPetView view;
    private Pet pet;

    public PetController(VirtualPetView view) {
        this.view = view;
        this.pet = new Pet();
    }

    public void onMouseMove(MouseEvent event) {
        double x = event.getX() - 50;
        double y = event.getY() - 50;

        Platform.runLater(() -> {
            view.movePet(x, y);
        });
    }

    public double getPetX() {
        return pet.getX();
    }

    public double getPetY() {
        return pet.getY();
    }

    public void setPetLocation(double x, double y, boolean flip) {
        pet.setX(x);
        pet.setY(y);
        Platform.runLater(() -> {
            view.updatePetLocation(x, y, flip);
        });
    }
}
