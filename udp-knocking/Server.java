import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;

public class Server {
    public static void main(String[] args) throws IOException {
        // config
        int [] sequence = new int[args.length];
        HashSet<Integer> ports = new HashSet<>();

        // connection objects
        ArrayList<DatagramSocket> sockets = new ArrayList<>();

        // set sequence
        for (int i = 0; i < args.length; i++)
            sequence[i] = Integer.parseInt(args[i]);

        // set ports
        for (String arg : args)
            ports.add(Integer.parseInt(arg));


        // OPEN DATAGRAM PORTS
        for (Integer port : ports) {
            DatagramSocket socket = new DatagramSocket(port);
            sockets.add(socket);
        }



//        boolean running;
//        byte[] buf = new byte[256];
//
//        running = true;
//
//        while (running) {
//            DatagramPacket packet
//                    = new DatagramPacket(buf, buf.length);
//            socket.receive(packet);
//
//            InetAddress address = packet.getAddress();
//            int port = packet.getPort();
//            packet = new DatagramPacket(buf, buf.length, address, port);
//            String received = new String(packet.getData(), 0, packet.getLength());
//
//            System.out.println("received: " + received);
//
//            if (received.equals("end")) {
//                running = false;
//                continue;
//            }
//            socket.send(packet);
//        }
//        socket.close();

    }
}