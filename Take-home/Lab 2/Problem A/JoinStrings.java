import java.util.*;

public class JoinStrings{

    static void printAll(List<ArrayList<Integer>> order, Hashtable<Integer, String> str, int idx, Kattio k){
        k.print(str.get(idx));
        for (int i : order.get(idx)){
            printAll(order, str, i, k);
        }
    }

    public static void main(String[] args){
        Kattio k = new Kattio(System.in, System.out);
        int noOfStrings = k.getInt();
        Hashtable<Integer, String> str = new Hashtable<Integer, String>();

        List<ArrayList<Integer>> order = new ArrayList<>(); //stores the order of concatenation to be done on each string

        for (int i = 0; i < noOfStrings; i++){
            str.put(i, k.getWord());
            order.add(new ArrayList<Integer>());
        }

        int s = 0; //it is the last processing to be done - aka smth must be added to a starting word cuz all the strings are "joined" to a starting string

        for(int i = 0 ; i < noOfStrings - 1; i++){
            int firstIdx = k.getInt() - 1;
            int secondIdx = k.getInt() - 1;
            order.get(firstIdx).add(secondIdx);
            s = firstIdx;
        }

        printAll(order, str, s, k);

        k.flush();
    }
}
