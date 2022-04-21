package work1.figures;

public class Round extends Figure {
    private final double radius;

    public Round(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}
