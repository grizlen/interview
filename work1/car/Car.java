package work1.car;

public abstract class Car implements Moveable, Stopable{

    private Engine engine;
    private String color;
    private String name;

    protected void log(String msg) {
        System.out.println(getClass().getSimpleName() + ": " + msg + '.');
    }
    public void start() {
        log("Starting");
    }

    public void open() {
        log("Open");
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
