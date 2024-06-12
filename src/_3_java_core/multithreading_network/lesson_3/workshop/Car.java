package _3_java_core.multithreading_network.lesson_3.workshop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Car implements Serializable {
    private final static long serialVersionUID = 1L;
    private String name;
    private int year;
    Engine engine;
    private String owner;
    private int price;

    public Car() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Car(String name, int year, Engine engine) {
        this.name = name;
        this.year = year;
        this.engine = engine;
    }

    @XmlElement
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @XmlElement
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
