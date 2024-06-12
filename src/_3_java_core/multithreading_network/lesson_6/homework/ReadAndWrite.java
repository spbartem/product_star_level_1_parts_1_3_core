package _3_java_core.multithreading_network.lesson_6.homework;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class ReadAndWrite {
    public static void readAndWrite() throws IOException {

        File in = new File("in.txt");
        File out = new File("out.txt");


        try (FileChannel inChannel = new FileInputStream(in).getChannel();
             FileChannel outChannel = new FileOutputStream(out).getChannel()) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int bufferSize = 1024;
            if (bufferSize > inChannel.size()) {
                bufferSize = (int) inChannel.size();
            }
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);

            while (inChannel.read(buff) > 0) {
                byteArrayOutputStream.write(buff.array(), 0, buff.position());
                buff.clear();
            }

            ByteBuffer wrap = ByteBuffer.wrap("Hello world".getBytes(StandardCharsets.UTF_8));

            outChannel.write(wrap);

        } catch (IOException e) {
            throw e;
        }

    }
}
