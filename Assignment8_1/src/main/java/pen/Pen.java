package pen;

public class Pen {
    public enum Color {
        RED("red"), GREEN("green"), BLUE("blue");
        private final String color;
        Color(String color) { this.color = color; };
        @Override public String toString() { return color; }
    }

    // your code here

    private Color color;
    private boolean canDraw;


    public Pen() {
        color = Color.RED;
        canDraw = false;
    }

    public Pen(Color color) {
        this.color = color;
        canDraw = false;
    }

    public void capOn() {
        canDraw = false;
    }

    public void capOff() {
        canDraw = true;
    }

    public String draw() {
        if (color != null && canDraw)
            return "Drawing " + color;
        else
            return "";
    }

    public void changeColor(Color color) {
        this.color = color;
    }
}