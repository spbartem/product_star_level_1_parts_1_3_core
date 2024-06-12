package _3_java_core.multithreading_network.lesson_5.workshop;

public class Stock {
    private String date;
    private float value;

    public Stock() {
    }

    public Stock(String date, float value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
