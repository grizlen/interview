package work1.car;

public class LightWeightCar extends Car{
    @Override
    public void move() {
        log("Move");
    }

    @Override
    public void stop() {
        log("Stop");
    }
}
