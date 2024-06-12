package _3_java_core.multithreading_network.lesson_3.workshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class CarYaml {
    public static void main(String[] args) throws IOException {
        Engine engine = new Engine();
        engine.setHp(250);

        Car miura = new Car("Miura", 1966, engine);

        File file = new File("C:\\Users\\user\\Documents\\java_classes\\ProductStar\\src\\_3_java_core\\" +
                "multithreading_network\\lesson_3\\workshop\\tmp\\miura.yaml");

        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        om.writeValue(file, miura);

        ///////

        Car newMiura = om.readValue(file, Car.class);
        System.out.println(newMiura.getName() + "\n" + newMiura.getEngine().getHp());
    }
}
