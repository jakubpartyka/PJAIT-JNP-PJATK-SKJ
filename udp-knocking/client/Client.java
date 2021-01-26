package client;

import java.io.IOException;
import java.net.*;

public class Client {
    // CONFIGURATION
    private static final int RESPONSE_PORT = 50100;
    private static final int TIMEOUT = 2000;            // server response timeout in milliseconds

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
        knockOnPort(50003);

        try {
            int portNumber = Integer.parseInt(receive().trim());
            System.out.println("Authorized successfully. Received TCP port number: " + portNumber);
        } catch (SocketTimeoutException e){
            System.out.println("Connection timed out, server did not respond. ");
        }
    }

    private static String receive() throws IOException {
        System.out.println("waiting for server response");

        DatagramSocket socket = new DatagramSocket(RESPONSE_PORT);
        socket.setSoTimeout(TIMEOUT);                               //set socket timeout

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
