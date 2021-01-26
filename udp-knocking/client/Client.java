package client;

import java.io.*;
import java.net.*;

public class Client {
    // CONFIGURATION
    private static final int RESPONSE_PORT = 50100;
    private static final int TIMEOUT = 2000;            // server response timeout in milliseconds
    private static String message = "Ala ma kota";

    // CONNECTION OBJECTS
    private static InetAddress SERVER_ADDRESS;

    public static void main(String[] args) throws IOException, InterruptedException {
        SERVER_ADDRESS = InetAddress.getByName("localhost");

        knockOnPort(20202);
        Thread.sleep(100);
        knockOnPort(20203);
        Thread.sleep(100);
        knockOnPort(40500);
        Thread.sleep(100);
        knockOnPort(50002);

        try {
            int portNumber = Integer.parseInt(receive().trim());
            System.out.println("Authorized successfully. Received TCP port number: " + portNumber);

            // connect to server over TCP
            String response = reverseStringOverTcp(portNumber);
            System.out.println("received server response: " + response);
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
        DatagramPacket request = new DatagramPacket(buffer, buffer.length, SERVER_ADDRESS, port);
        socket.send(request);
    }

    public static String reverseStringOverTcp(int port) throws IOException {
        // connect to server
        Socket socket = new Socket(SERVER_ADDRESS,port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

        // send
        writer.println(message);

        // receive
        return reader.readLine();
    }
}
