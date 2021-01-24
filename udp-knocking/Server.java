import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Server {
    // SERVER CONFIGURATION
    public static final String KNOCK_MESSAGE = "KNOCK KNOCK";
    public static final Integer MIN_ACCEPTABLE_PORT = 1025;

    public static void main(String[] args) throws IOException, InterruptedException {
        // =========== CONNECTION OBJECTS
        SequenceSupervisor.sequence = new int[args.length];
        HashSet<Integer> ports = new HashSet<>();
        ArrayList<ServerSocketThread> sockets = new ArrayList<>();
        // ===========

        // check if any args were passed
        if(args.length == 0){
            System.out.println("Please provide UPD knocking sequence as program arguments");
            System.exit(1);
        }

        // set sequence
        for (int i = 0; i < args.length; i++)
            SequenceSupervisor.sequence[i] = Integer.parseInt(args[i]);

        // set ports
        for (String arg : args) {
            // check if provided port number is correct
            int portNumber = Integer.parseInt(arg);
            if(portNumber < MIN_ACCEPTABLE_PORT)
                throw new IllegalArgumentException("Specified port list contains port number below minimal acceptable value");
            // add port number to ports list
            ports.add(Integer.parseInt(arg));
        }


        // START EACH SOCKET IN NEW THREAD
        for (Integer port : ports) {
            System.out.println("Starting socket on port " + port);
            ServerSocketThread socketThread = new ServerSocketThread(port);
            socketThread.start();
            sockets.add(socketThread);
        }

        Thread.sleep(1000);
        //todo check if threads started correctly

        System.out.println("All sockets stared. Correct UPD knock sequence:\n" +
                Arrays.toString(SequenceSupervisor.sequence));
    }
}
