import java.util.*;

public class Conformity{

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int noOfFrosh = sc.nextInt();

        while(sc.hasNext()){
            // Create a map containing the frequency of the course combinations
            HashMap<String, Integer> f = new HashMap<>();

            // For each frosh get their course combination and add to f
            for(int i = 0; i < noOfFrosh; i++){
                ArrayList<Integer> courseCombination = new ArrayList<>();
                for(int j = 0; j < 5; j++){
                    courseCombination.add(sc.nextInt());
                }

                Collections.sort(courseCombination);
                String uniqueKey = courseCombination.toString();

                if(f.containsKey(uniqueKey)){
                    f.put(uniqueKey, f.get(uniqueKey) + 1);
                }else{
                    f.put(uniqueKey, 1);
                }
            }

            // Find the frequency of the most popular combination
            int mostPopularCombo = 0;
            for(int v : f.values()){
                mostPopularCombo = Math.max(mostPopularCombo, v);
            }

            // Calculate the number of students taking those combinations
            int countFrosh = 0;
            for(int v : f.values()){
                if(v == mostPopularCombo){
                    countFrosh = countFrosh + v;
                }
            }
            System.out.print(countFrosh);
        }
        sc.close();

    }

}
