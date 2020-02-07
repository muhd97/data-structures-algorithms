import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
public class Main {
	
	// Function to determine how much gold can be obtained from Session with energy a
	public static long goldAmt(int a, TreeMap<Integer, PriorityQueue<Quest>> m) {
		long gold = 0;		
		while(a > 0) {
			if (m.isEmpty()) {
				break;
			}
			else if (m.floorKey(a) == null) {
					break;
			}
			else {
				int curr_key = m.floorKey(a);								
				if (m.get(curr_key).isEmpty()) {
					m.remove(curr_key);
				}
				
				else if (m.get(curr_key) != null) {
					PriorityQueue<Quest> curr = m.get(curr_key);
					int curr_energy = curr.peek().getE();
					a -= curr_energy;
					long curr_gold = (long) curr.peek().getG();
					gold += curr_gold;
					curr.remove();
				}
			}
		}
		return gold;
	}
				
	public static void main(String[] args) {		
		Kattio io = new Kattio(System.in, System.out);
		
		// Number of commands
		int n = io.getInt();	
		TreeMap<Integer, PriorityQueue<Quest>> questMap = new TreeMap<Integer, PriorityQueue<Quest>>();
		ArrayList<Long> results = new ArrayList<Long>();
		
		for(int i = 0; i < n; i++) {
			String command = io.getWord();			
			if(command.equals("add")) {
				Quest q = new Quest(io.getInt(),io.getInt());
				int key = q.getE();
				PriorityQueue<Quest> temp = questMap.get(key);
				
				//check if there is such a PQ in the map, if have then add quest to PQ, if not create a new PQ
				if (temp == null) {
					temp = new PriorityQueue<Quest>();
					temp.add(q);
					questMap.put(key, temp);
				}
				else {
					temp.add(q);
				}				
			}			
			else if(command.equals("query")) {
				results.add(goldAmt(io.getInt(), questMap));			
			}
		}
		
		for(long i: results) {
			io.println(i);
		}
		io.flush();
		
	}
}
