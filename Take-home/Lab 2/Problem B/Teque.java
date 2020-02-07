import java.util.*;
import java.io.*;

public class Teque{

    private static HashMap<Integer, Integer> frontq = new HashMap<>();
    private static int minFrtIdx = 0; //first index of the front queue
    private static int maxFrtIdx = 0;

    private static HashMap<Integer, Integer> backq = new HashMap<>();
    private static int minBckIdx = 0;
    private static int maxBckIdx = 0; //last index of the back queue

    private static void push_front(int x){
        minFrtIdx--;
        frontq.put(minFrtIdx, x);
        balance();
    }

    private static void push_back(int x){
        backq.put(maxBckIdx, x);
        maxBckIdx++;
        balance();
    } 

    //balance the size of the frontqueue and backqueue
    private static void balance(){
        if (frontq.size() > backq.size()){
            maxFrtIdx--;
            minBckIdx--;
            backq.put(minBckIdx, frontq.get(maxFrtIdx));
            frontq.remove(maxFrtIdx);
            return;
        }
        if (backq.size() > frontq.size() + 1){
            frontq.put(maxFrtIdx, backq.get(minBckIdx));
            maxFrtIdx++;
            minBckIdx++;
            backq.remove(minBckIdx - 1);
            return;
        }
    }

    private static void push_middle(int x){
        if( (frontq.size() == backq.size()) || (frontq.size() > backq.size()) ){
            minBckIdx--;
            backq.put(minBckIdx, x);
        }else{
            frontq.put(maxFrtIdx, backq.get(minBckIdx));
            maxFrtIdx++;
            backq.put(minBckIdx, x);
        }
    }

    private static int get(int index){
        if (index < (maxFrtIdx - minFrtIdx) ){
            return frontq.get(minFrtIdx + index);
        }
        index -= (maxFrtIdx - minFrtIdx);
        return backq.get(minBckIdx + index);
    }

    public static void main(String[] args) throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int numOfOperations = Integer.parseInt(bf.readLine());

        for (int i = 0; i < numOfOperations; i++){
            String[] command = bf.readLine().split(" ");

            if(command[0].equals("get")){
                out.write(Integer.toString(get(Integer.parseInt(command[1]))) + '\n');
            }else if(command[0].equals("push_front")){
                push_front(Integer.parseInt(command[1]));
            }else if(command[0].equals("push_back")){
                push_back(Integer.parseInt(command[1]));           
            }else{
                push_middle(Integer.parseInt(command[1]));
            }
        }    
        out.close();
    }

}
