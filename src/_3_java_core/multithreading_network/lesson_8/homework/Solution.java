package _3_java_core.multithreading_network.lesson_8.homework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Напишите класс, который бы на основе рефлексии создавал новые экземпляры DZ, обращался бы к их методам
 * getSumInteger и  getSumFromList
 * и выводил результаты в консоль
 */

public class Solution {
    private int i;
    private String s;
    private List<Double> list = new ArrayList<>();

    private Solution() {
    }

    public Solution(Integer i, String s) {
        this.i = i;
        this.s = s;
    }

    public Solution(Integer i, String s, List<Double> list) {
        this.i = i;
        this.s = s;
        this.list = list;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public List<Double> getList() {
        return list;
    }

    public void setList(List<Double> list) {
        this.list = list;
    }

    private int getSumInteger(Solution dz1, Solution dz2) {
        return dz1.getI() + dz2.getI();
    }

    private List<Double> getSumFromList(Solution dz1, Solution dz2) {
        return Stream.concat(dz1.getList().stream(), dz2.getList().stream()).collect(Collectors.toList());
    }

}

class ReflectionDZ {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Constructor constructor = Solution.class.getConstructor(Integer.class, String.class); // Solution(int i, String s)

        Solution builder = (Solution)
                constructor.newInstance(1, "string");
        Solution builder2 = (Solution)
                constructor.newInstance(2, "string");

        // просто получение информации

        Constructor[] constructors = Solution.class.getConstructors();
        Field[] fields = Solution.class.getFields();
        Method[] methods = Solution.class.getDeclaredMethods();

        // просто получение информации


        /**
         * Доступ к закрытым методам осуществляется с помощью методов Class.getDeclaredMethod(String name, Class[] parameterTypes) или Class.getDeclaredMethods().
         */


        Method getSumInteger = Solution.class.
                getDeclaredMethod("getSumInteger", new Class[]{Solution.class, Solution.class});
        getSumInteger.setAccessible(true);
        int sumInteger = (Integer)
                getSumInteger.invoke(builder, builder, builder2);
        System.out.println(sumInteger);


        Constructor constructor2 = Solution.class.getConstructor(Integer.class, String.class, List.class); // DZ_Solution(Integer i, String s, List<Double> list)

        Solution builder3 = (Solution)
                constructor2.newInstance(1, "string", Arrays.asList(1.2, 45.6, 33, 9));
        Solution builder4 = (Solution)
                constructor2.newInstance(1, "string", Arrays.asList(1.2, 45.6, 33, 9));

        Method getSumFromList = Solution.class.
                getDeclaredMethod("getSumFromList", new Class[]{Solution.class, Solution.class});
        getSumFromList.setAccessible(true);
        List<Double> doubles = (List<Double>) getSumFromList.invoke(builder3, builder3, builder4);
        System.out.println(doubles);
    }
}