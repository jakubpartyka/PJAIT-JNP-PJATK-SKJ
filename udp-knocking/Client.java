import java.io.IOException;
import java.net.*;

public class Client {
    // CONFIGURATION
    private static final String KNOCK_MESSAGE = "KNOCK KNOCK";

    // CONNECTION OBJECTS
    private static DatagramSocket socket;
    private static InetAddress address;


    public static void main(String[] args) throws IOException, InterruptedException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");

        knockOnPort(20202);
        Thread.sleep(100);
        knockOnPort(20203);
        Thread.sleep(100);
        knockOnPort(40500);
        Thread.sleep(100);
        knockOnPort(50002);

    }

    public static void knockOnPort(int port) throws IOException {
        byte[] buffer = KNOCK_MESSAGE.getBytes();

        // create and send packet
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);


    }

    public static int waitForPortNumber() throws IOException, NumberFormatException {
        byte[] buffer = new byte[256];

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());

        return Integer.parseInt(received);
    }
}
