import java.util.*;         

public class BestRelayTeam{

    public static Pair<Vector<String>, Double> rest(Vector<Runner> v, int skip) {//this method will take one runners first timing, remove him and sort(in less timings) and then take the 
        Vector<Runner> r = new Vector<>(v);                                      // other threes and find total and reset and repeat for next runner
        Vector<String> ans = new Vector<>();                                     //so u get all the combos where everyone runner first timing will be added with second of remaining 3s to get all combinations
        ans.add(r.get(skip).name);
        Double total = r.get(skip).first;

        r.remove(skip); 
        Collections.sort(r);

        total += r.get(0).second;
        total += r.get(1).second;
        total += r.get(2).second;

        ans.add(r.get(0).name);
        ans.add(r.get(1).name);
        ans.add(r.get(2).name);

        Pair<Vector<String>, Double> p = new Pair<>(ans, total);
        return p;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Vector<Runner> runners = new Vector<>(n);
        for(int i=0; i<n; i++){
            runners.add(i, new Runner(sc.next(), sc.nextDouble(), sc.nextDouble()));
        }

        double best = 10000;
        Vector<String> BestTeam = new Vector<>();
        for(int i=0; i<n; i++){
            Pair<Vector<String>, Double> here = rest(runners, i);
            if(here.second < best) {
                best = here.second;
                BestTeam = here.first;
            }
        }
        System.out.println(best);
        for(String str: BestTeam){
            System.out.println(str);
        }

    }
}

class Runner implements Comparable<Runner>{

    public String name;
    public Double first;
    public Double second;

    public Runner(String name, Double first, Double second){
        this.name = name;
        this.first = first;
        this.second = second;
    }

    @Override
        public int compareTo(Runner r){
            return Double.compare(this.second,r.second);
        }
}

class Pair<First, Second> {
    public First first;
    public Second second;

    public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }
}



