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

    // Селектор представляет механизм для мониториннга одного или нескольких каналов NIO
    //
    // Один поток может искользоваться для управления несколькими каналами и,
    // следовательно, несколькими сетевыми подключениями.

    public static void main(String[] args) throws IOException {
        // Переключение контекста между потоками является дорогостоящим для операционной системы,
        // каждый поток занимает память.
        //
        // Селекторы не просто помогают вам читать данные; они также могут прослушивать входящие сетевые соединения
        // и записывать данные по каналам.

        Selector selector = Selector.open();

        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 5454));
        serverSocket.configureBlocking(false);

        /*
         * Мы можем различные события, каждое из которых представлено константой в классе SelectionKey:
         *
         * Connect - клиент пытается подключиться к серверу.
         *
         * SelectionKey.OP CONNECT Accept__ - ** когда сервер принимает соединение от клиента.
         *
         * SelectionKey.OP CONNECT Read__ - ** когда сервер готов к чтению с канала.
         *
         * SelectionKey.OP CONNECT Write__ - ** когда сервер готов к записи в канал.
         */

        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        // Java NIO использует буферно-ориентированную модель, отличную от потоково-ориентированной модели.
        // Поэтому общение через сокеты происходит путём записи в буфер и чтения из него.
        //
        // Создаём ByteBuffer, с которого сервер будет писать и читать.

        ByteBuffer byteBuffer = ByteBuffer.allocate(256);

        while (true) {
            selector.select();

            // получаем набор выбранных ключей для обработки

            Set<SelectionKey> selectedKeys  = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();

            while (iter.hasNext()) {

                // Чтобы селектор мог отслеживать любые канала,
                // мы должны зарегистрировать эти каналы в селекторе

                SelectionKey key = iter.next();

                // набор событий, которые мы хотим, чтобы селектор наблюдал в этом канале

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
