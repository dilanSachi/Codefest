package sample;

import java.util.ArrayList;
import java.util.List;

public class DummyDB {
    public static List<Car> getListOfCars() {
        List<Car> list = new ArrayList<Car>();
        list.add(new Car(1, "X6 M", "BMW", 78, "2011"));
        list.add(new Car(2, "340i", "BMW", 32, "2011"));
        list.add(new Car(3, "Veyron 16.4 GS Vi", "BMW", 25, "2014"));
        list.add(new Car(4, "SRX 3.0", "Cadillac", 14, "2015"));
        list.add(new Car(5, "CTS-V Coupe", "Cadillac", 10, "2009"));
        list.add(new Car(6, "Escalate Hybrid", "Cadillac", 5, "2012"));
        list.add(new Car(7, "Stingray", "Corvette", 31, "2015"));
        list.add(new Car(8, "Camaro Z/28", "Corvette", 78, "2012"));
        list.add(new Car(9, "Z06 Coupe", "Corvette", 69, "2013"));
        list.add(new Car(10, "488 GTB", "Ferrari", 1, "2013"));
        list.add(new Car(11, "458 Spec Aperta", "Ferrari", 56,"2010"));
        list.add(new Car(12, "Aventadore SV", "Lamborghini", 7, "2011"));
        list.add(new Car(13, "H LP610-4", "Lamborghini", 9,"2011"));
        return list;
    }
}