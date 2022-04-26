package work1;

import work1.builder.Person;
import work1.car.Car;
import work1.car.LightWeightCar;
import work1.car.Lorry;
import work1.figures.Figure;
import work1.figures.Round;
import work1.figures.Square;
import work1.figures.Triangle;

public class Main {
    public static void main(String[] args) {
        // 1
        System.out.println(
                Person.builder("FirstName", 111)
                        .lastName("LastName")
                        .middleName("MiddleName")
                        .country("Country")
                        .address("Address")
                        .phone("Phone")
                        .gender("Gender")
                        .build());

        //2
        Car car = new LightWeightCar();
        car.start();
        car.move();
        car.stop();
        car.open();

        car = new Lorry();
        car.start();
        car.move();
        car.stop();
        car.open();

        //3
        Figure figure = new Square(4.0);
        System.out.println("Площадь квадрата 4Х4 = " + figure.area());

        figure = new Triangle(4.0, 5.0);
        System.out.println("Площадь треугольника осн: 4 высота: 5 = " + figure.area());

        figure = new Round(6.0);
        System.out.println("Площадь круга радиус: 6 = " + figure.area());
    }
}
