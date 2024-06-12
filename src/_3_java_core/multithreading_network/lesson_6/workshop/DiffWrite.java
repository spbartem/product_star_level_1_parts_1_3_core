package _3_java_core.multithreading_network.lesson_6.workshop;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiffWrite {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");

    public static void main(String[] args) throws IOException {
        File in = new File("C:\\projects\\java\\JavaProductStar\\src\\_3_java_core\\multithreading_network" +
                "\\lesson_6\\workshop\\in.txt");
        File out = new File("C:\\projects\\java\\JavaProductStar\\src\\_3_java_core\\multithreading_network" +
                "\\lesson_6\\workshop\\out.txt");
        copyFile(in, out);
        System.out.println("=====================================");
        copyFileNio(in, out);
    }

    private static void copyFile(File in, File out) throws IOException {
        System.out.println("Before Read: " + sdf.format(new Date()));

        try (FileChannel inChannel = new FileInputStream(in).getChannel();
             FileChannel outChannel = new FileOutputStream(out).getChannel()) {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            throw e;
        }

        System.out.println("After Read IO: " + sdf.format(new Date()));
    }

    private static void copyFileNio(File in, File out) throws IOException {
        System.out.println("Before Read: " + sdf.format(new Date()));

        try (FileInputStream fis = new FileInputStream(in);
             FileOutputStream fos = new FileOutputStream(out)) {
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            throw e;
        }

        System.out.println("After Read NIO: " + sdf.format(new Date()));
    }
}
