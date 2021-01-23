import java.util.ArrayList;
import java.util.HashMap;

public class SequenceSupervisor {
    HashMap<String, ArrayList<Integer>> sequences = new HashMap<>();


    void addSequence(String clientAddress, int port){
        if(!sequences.containsKey(clientAddress))
            sequences.put(clientAddress, new ArrayList<>());
        sequences.get(clientAddress).add(port);
    }
}
