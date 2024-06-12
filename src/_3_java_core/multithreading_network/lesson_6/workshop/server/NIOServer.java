package _3_java_core.multithreading_network.lesson_6.workshop.server;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private static final String POISON__PILL = "POISON__PILL";

    // �������� ������������ �������� ��� ������������ ������ ��� ���������� ������� NIO
    //
    // ���� ����� ����� �������������� ��� ���������� ����������� �������� �,
    // �������������, ����������� �������� �������������.

    public static void main(String[] args) throws IOException {
        // ������������ ��������� ����� �������� �������� ������������� ��� ������������ �������,
        // ������ ����� �������� ������.
        //
        // ��������� �� ������ �������� ��� ������ ������; ��� ����� ����� ������������ �������� ������� ����������
        // � ���������� ������ �� �������.

        Selector selector = Selector.open();

        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 5454));
        serverSocket.configureBlocking(false);

        /*
         * �� ����� ��������� �������, ������ �� ������� ������������ ���������� � ������ SelectionKey:
         *
         * Connect - ������ �������� ������������ � �������.
         *
         * SelectionKey.OP CONNECT Accept__ - ** ����� ������ ��������� ���������� �� �������.
         *
         * SelectionKey.OP CONNECT Read__ - ** ����� ������ ����� � ������ � ������.
         *
         * SelectionKey.OP CONNECT Write__ - ** ����� ������ ����� � ������ � �����.
         */

        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        // Java NIO ���������� �������-��������������� ������, �������� �� ��������-��������������� ������.
        // ������� ������� ����� ������ ���������� ���� ������ � ����� � ������ �� ����.
        //
        // ������ ByteBuffer, � �������� ������ ����� ������ � ������.

        ByteBuffer byteBuffer = ByteBuffer.allocate(256);

        while (true) {
            selector.select();

            // �������� ����� ��������� ������ ��� ���������

            Set<SelectionKey> selectedKeys  = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();

            while (iter.hasNext()) {

                // ����� �������� ��� ����������� ����� ������,
                // �� ������ ���������������� ��� ������ � ���������

                SelectionKey key = iter.next();

                // ����� �������, ������� �� �����, ����� �������� �������� � ���� ������

                if (key.isAcceptable()) {
                    register(selector, serverSocket);
                }

                if (key.isReadable()) {
                    answerWithEcho(byteBuffer, key);
                }
                iter.remove();
            }
        }
    }

    private static void answerWithEcho(ByteBuffer byteBuffer, SelectionKey key) throws IOException {

        SocketChannel client = (SocketChannel) key.channel();
        client.read(byteBuffer);

        if (new String(byteBuffer.array()).trim().equals(POISON__PILL)) {
            client.close();
            System.out.println("No accepting client messages anymore");
        }

        byteBuffer.flip();
        client.write(byteBuffer);
        byteBuffer.clear();
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    public static Process start() throws IOException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = NIOServer.class.getCanonicalName();

        ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);

        return builder.start();
    }
}
