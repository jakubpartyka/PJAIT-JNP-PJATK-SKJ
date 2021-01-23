import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 10 ; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    DatagramSocket socket;
                    InetAddress address;
                    byte[] buf;

                    socket = new DatagramSocket();
                    address = InetAddress.getByName("localhost");

                    String msg = "test " + finalI;

                    //noinspection DuplicatedCode
                    buf = msg.getBytes();
                    DatagramPacket packet
                            = new DatagramPacket(buf, buf.length, address, 4445);
                    socket.send(packet);
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    String received = new String(
                            packet.getData(), 0, packet.getLength());

                    System.out.println(received);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
