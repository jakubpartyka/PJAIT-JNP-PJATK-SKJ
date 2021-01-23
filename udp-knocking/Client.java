import java.io.IOException;
import java.net.*;

public class Client {
    private static DatagramSocket socket;
    private static InetAddress address;
    private static byte[] buf;

    public static void main(String[] args) throws IOException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");

        String response = sendEcho("KNOCK KNOCK");
        System.out.println(response);
    }

    public static String sendEcho(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 20202);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        return received;
    }
}
