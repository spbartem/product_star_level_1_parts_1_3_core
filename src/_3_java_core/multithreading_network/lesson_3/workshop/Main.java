package _3_java_core.multithreading_network.lesson_3.workshop;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Engine engine = new Engine();
        engine.setHp(150);

        Car fordFocus = new Car("Ford Focus", 2020, engine);

        FileOutputStream fos = new FileOutputStream(
                new File("C:\\Users\\user\\Documents\\java_classes\\ProductStar\\src\\_3_java_core\\" +
                        "multithreading_network\\lesson_3\\workshop\\tmp\\car.ser"));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(fordFocus);
        oos.close();
    }
}
