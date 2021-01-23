import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerSocketThread extends Thread{
    DatagramSocket datagramSocket;
    boolean running;

    public ServerSocketThread(int port) throws SocketException {
        this.datagramSocket = new DatagramSocket(port);
        this.running = true;
    }

    @Override
    public void run() {
        try {
            while (running){
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());

                System.out.println("received: " + received);

                running = !received.equals("end");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
