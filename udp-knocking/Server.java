import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Server {
    public static final String KNOCK_MESSAGE = "KNOCK KNOCK";

    public static void main(String[] args) throws IOException {
        // check if any args were passed
        if(args.length == 0){
            System.out.println("Please provide UPD knocking sequence as program arguments");
            System.exit(1);
        }

        // config
        int [] sequence = new int[args.length];
        HashSet<Integer> ports = new HashSet<>();

        // connection objects
        ArrayList<ServerSocketThread> sockets = new ArrayList<>();

        // set sequence
        for (int i = 0; i < args.length; i++)
            sequence[i] = Integer.parseInt(args[i]);

        // set ports
        for (String arg : args)
            ports.add(Integer.parseInt(arg));


        // START EACH SOCKET IN NEW THREAD
        for (Integer port : ports) {
            System.out.println("Starting socket on port " + port);
            ServerSocketThread socketThread = new ServerSocketThread(port);
            sockets.add(socketThread);
            socketThread.start();
        }
    }
}
