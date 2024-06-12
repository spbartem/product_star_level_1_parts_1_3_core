package _2_object_and_class.lesson_6.homework;

public class Main {
    public final static char ch = 'e';
    public final static String text = "1. В тексте, который вы видите на этом изображении," +
            "посчитайте количество букв 'е' в каждом слове" +
            "2. Напишите регулярое выражение для проверки телефона в международном формате" +
            "3. С помощью регулярного выражения напишите функцию удаления всех букв и пробелов из текста";

    public static void main(String[] args) {
        countLetterInEachWord(text, ch);
    }

    public static void countLetterInEachWord(String text, char letter) {
        String[] words = text.split("\\s+");
        for (String word : words) {
            int count = countLetter(word, letter );
            System.out.printf("%s: %d%s", word, count, count == 0 ? "" : " ");
        }
    }

    public static int countLetter(String str, char letter) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == letter) {
                count++;
            }
        }
        return count;
    }
}
