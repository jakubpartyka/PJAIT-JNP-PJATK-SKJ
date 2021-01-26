import java.io.IOException;
import java.net.*;

public class Client {

    // CONNECTION OBJECTS
    private static InetAddress address;
    private static DatagramPacket packet;


    public static void main(String[] args) throws IOException, InterruptedException {
        address = InetAddress.getByName("localhost");

        knockOnPort(20202);
        Thread.sleep(100);
        knockOnPort(20203);
        Thread.sleep(100);
        knockOnPort(40500);
        Thread.sleep(100);
        knockOnPort(50002);

        String response = receive();

        System.out.println("Authorized successfully. Received TCP port number: " + response);

    }

    private static String receive() throws IOException {
        DatagramSocket socket = new DatagramSocket(50100);

        byte[] buffer = new byte[256];
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        socket.receive(request);
        return new String(request.getData());
    }

    public static void knockOnPort(int port) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        DatagramSocket socket = new DatagramSocket();

        byte[] buffer = new byte[256];

        DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(request);
    }

    public static int waitForPortNumber() throws IOException, NumberFormatException {
        byte [] buffer = new byte[5];
        packet = new DatagramPacket(buffer, buffer.length);
        String received = new String(packet.getData(), 0, packet.getLength());

        return Integer.parseInt(received);
    }
}
