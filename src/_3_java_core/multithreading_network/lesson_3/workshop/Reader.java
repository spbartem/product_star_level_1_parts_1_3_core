package _3_java_core.multithreading_network.lesson_3.workshop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Reader {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(
                "C:\\Users\\user\\Documents\\java_classes\\ProductStar\\src\\_3_java_core\\" +
                "multithreading_network\\lesson_3\\workshop\\tmp\\car.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Car car = (Car) ois.readObject();

        System.out.println(car.getName() + "\n" + car.getYear() + "\n" + car.getEngine().getHp());
    }
}
