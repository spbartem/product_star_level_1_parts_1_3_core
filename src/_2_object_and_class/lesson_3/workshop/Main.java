package _2_object_and_class.lesson_3.workshop;

import _2_object_and_class.lesson_3.workshop.geometry.Box;

public class Main {
    public static void main(String[] args) {
        Box box = new Box(1,2,3);
        System.out.println(box.getVolume());
    }
}
