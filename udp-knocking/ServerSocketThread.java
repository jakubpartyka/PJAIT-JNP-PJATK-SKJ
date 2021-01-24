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

                // get sender's data
                String clientAddress =  address.getHostAddress();
                int knockPort = packet.getPort();

                // obtain received message
                String received = new String(packet.getData(), 0, Server.KNOCK_MESSAGE.length());


                if(received.equals(Server.KNOCK_MESSAGE)) {     // if message content is equal to preset knock message
                    log("knock-knock message received from: " + clientAddress + " on port " + knockPort + ". Adding to knock sequence.");
                    SequenceSupervisor.addSequence(clientAddress,knockPort);

                    if(SequenceSupervisor.verifySequence(clientAddress)){
                        log("client " + address + " just sent a correct sequence!");
                    }
                    else {
                        log("client " + address + " sequence so far: " + SequenceSupervisor.getClientSequence(clientAddress));
                    }
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
