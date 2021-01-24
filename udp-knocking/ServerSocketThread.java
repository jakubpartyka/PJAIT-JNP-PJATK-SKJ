import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerSocketThread extends Thread{
    private final int port;
    DatagramSocket datagramSocket;
    boolean running;

    public ServerSocketThread(int port) throws SocketException {
        this.port = port;
        this.datagramSocket = new DatagramSocket(port);
        this.running = true;
    }

    @Override
    public void run() {
        try {
            while (running){
                byte[] buffer = new byte[256];     // create buffer for received message\
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                datagramSocket.receive(packet);    // receive incoming data

                InetAddress address = packet.getAddress();
                packet = new DatagramPacket(buffer, buffer.length, address, port);

                String received = new String(packet.getData(), 0, Server.KNOCK_MESSAGE.length());
                if(received.equals(Server.KNOCK_MESSAGE)) {
                    log("correct knock-knock message received from: " + address.getHostAddress() + ':' + packet.getSocketAddress());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String message){
        System.out.println("[Server Socket (" + port + ")]: " + message);
    }
}
