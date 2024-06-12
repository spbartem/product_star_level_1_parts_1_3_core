package _3_java_core.multithreading_network.lesson_3.workshop;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class CarXml {
    public static void main(String[] args) throws JAXBException {
        Engine engine = new Engine();
        engine.setHp(250);

        Car miura = new Car("Miura", 1966, engine);

        File file = new File("C:\\Users\\user\\Documents\\java_classes\\ProductStar\\src\\_3_java_core\\" +
                "multithreading_network\\lesson_3\\workshop\\tmp\\miura.xml");
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(miura, file);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        Car savedMiura = (Car) unmarshaller.unmarshal(file);

        System.out.println(savedMiura.getName() + "\n" + savedMiura.getEngine().getHp());
    }
}
