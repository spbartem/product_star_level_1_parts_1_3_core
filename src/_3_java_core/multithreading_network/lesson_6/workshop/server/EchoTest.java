package _3_java_core.multithreading_network.lesson_6.workshop.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class EchoTest {
    Process server;
    NIOClient client;

    @Before
    public void setup() throws IOException {
        server = NIOServer.start();
        client = NIOClient.start();
    }

    @Test
    public void givenServerClient__whenServerEchosMessage__thenCorrect() throws IOException {
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");

        assertEquals("hello", resp1);
        assertEquals("world", resp2);
    }

    @After
    public void teardown() throws IOException {
        server.destroy();
        NIOClient.stop();
    }
}
