import java.util.*;

public class TrainPassengers{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int n = sc.nextInt();
        int train = 0; //train capacity was not below 0  ;
        int l =0;
        int e =0; 
        int s =0;
        boolean possible = true;
        while(n>0){
            l = sc.nextInt();
            e = sc.nextInt();
            s = sc.nextInt();
            if(l > train){
                possible = false;
            }
            train -= l;
            if(train + e > c){
                possible = false;
            }
            train += e;
            if(s != 0 && train != c){
                possible = false;
            }
            n--;
        }

        if(e != 0 || s != 0 || train != 0){
            possible = false;
        }

        if(possible){
            System.out.println("possible");
        } else {
            System.out.println("impossible");
        }
    }
}








