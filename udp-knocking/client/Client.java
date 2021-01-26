package client;

import java.io.IOException;
import java.net.*;

public class Client {
    // CONFIGURATION
    private static final int RESPONSE_PORT = 50100;

    // CONNECTION OBJECTS
    private static InetAddress address;

    public static void main(String[] args) throws IOException, InterruptedException {
        address = InetAddress.getByName("localhost");

        knockOnPort(20202);
        Thread.sleep(100);
        knockOnPort(20203);
        Thread.sleep(100);
        knockOnPort(40500);
        Thread.sleep(100);
        knockOnPort(50002);

        int portNumber = Integer.parseInt(receive().trim());

        System.out.println("Authorized successfully. Received TCP port number: " + portNumber);

    }

    private static String receive() throws IOException {
        DatagramSocket socket = new DatagramSocket(RESPONSE_PORT);

        byte[] buffer = new byte[256];
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        socket.receive(request);
        return new String(request.getData());
    }

    public static void knockOnPort(int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] buffer = new byte[256];
        DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(request);
    }
}
