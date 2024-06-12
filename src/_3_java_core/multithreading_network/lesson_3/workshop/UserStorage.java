package _3_java_core.multithreading_network.lesson_3.workshop;

import java.io.*;

public class UserStorage {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User("myLogin", "777");

        FileOutputStream fos = new FileOutputStream(
                new File("C:\\Users\\user\\Documents\\java_classes\\ProductStar\\src\\_3_java_core\\" +
                        "multithreading_network\\lesson_3\\workshop\\tmp\\car.ser"));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);
        oos.close();

        /////////

        FileInputStream fis = new FileInputStream(
                "C:\\Users\\user\\Documents\\java_classes\\ProductStar\\src\\_3_java_core\\" +
                        "multithreading_network\\lesson_3\\workshop\\tmp\\car.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User savedUser = (User) ois.readObject();

        System.out.println(savedUser.getLogin() + "\n" + savedUser.getPassword());
    }
}
