import java.util.*;

public class CardTrick{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int numOfTestCases = sc.nextInt();
        for(int l = 0; l < numOfTestCases; l++){

            int numOfCards = sc.nextInt();
            LinkedList<Integer> stack = new LinkedList<>();
            int numOfShifts = numOfCards;

            for(int i = numOfCards; i>=1; i--){ //do everything in reverse order
                stack.push(i);                  //put last card in, shift by the number on last card by popping and pushing

                for(int j = numOfShifts; j >= 1; j--){
                    Integer s = stack.pollLast();
                    stack.push(s);
                }

                numOfShifts--;
            }


            for(Integer m : stack){
                System.out.print(m + " ");
            }

            if(l!= (numOfTestCases-1)){
                System.out.println();
            }
        }
    }
}


