package server;

import java.util.ArrayList;
import java.util.HashMap;

public class SequenceSupervisor {
    // SEQUENCE
    public static int [] sequence;

    // key: client address (ip:port) ; value: sequence list
    private static final HashMap<String, ArrayList<Integer>> clientSequences = new HashMap<>();

    // use this method when client knocked on port
    public static void addSequence(String clientAddress, int port){
        if(!clientSequences.containsKey(clientAddress))
            clientSequences.put(clientAddress, new ArrayList<>());
        clientSequences.get(clientAddress).add(port);
    }

    public static boolean verifySequence(String clientAddress){
        ArrayList<Integer> clientKnockOrder = clientSequences.get(clientAddress);

        if(clientKnockOrder.size() != sequence.length)
            return false;

        for (int i = 0; i < clientKnockOrder.size(); i++) {
            if (clientKnockOrder.get(i) != sequence[i])
                return false;
        }
        return true;
    }

    public static ArrayList<Integer> getClientSequence(String clientAddress){
        return clientSequences.get(clientAddress);
    }

    public static void resetClientSequence(String clientAddress){
        clientSequences.remove(clientAddress);
    }
}
