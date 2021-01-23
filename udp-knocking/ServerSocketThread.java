import javax.xml.crypto.Data;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerSocketThread extends Thread{
    DatagramSocket datagramSocket;

    public ServerSocketThread(int port) throws SocketException {
        this.datagramSocket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        super.run();
    }
}
