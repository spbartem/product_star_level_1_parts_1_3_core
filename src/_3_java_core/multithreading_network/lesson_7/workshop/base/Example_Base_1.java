package _3_java_core.multithreading_network.lesson_7.workshop.base;

public class Example_Base_1 {
    public static void main(String[] args) {
        ThreadExampleClass t1 = new ThreadExampleClass();
        Thread t2 = new Thread(new RunnableExample());
        System.out.println("Begin");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }
}
