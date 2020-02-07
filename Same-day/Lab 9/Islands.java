import java.util.*;

public class Islands{

    public static void main(String[] args){

        Kattio io = new Kattio(System.in, System.out);

        r = io.getInt();
        c = io.getInt();

        String image = ""; //is the entire input as a string
        for(int i = 0; i < r; i++){
            image+=io.getWord();
        }
        char[] imageArr = image.toCharArray(); //change the above string into characters
        int p = 0; //current when reading from imageArr

        //put in the characters
        v = new ArrayList<ArrayList<Character>>(r);
        for (int i = 0; i < r ;i++){
            v.add(new ArrayList<Character>(c));
        }
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c ; j++) {
                v.get(i).add(j,imageArr[p]);
                p++;
            }
        }

        //initialise visited as false
        visited = new ArrayList<ArrayList<Boolean>>(r);
        for(int i = 0; i < r; i++){
            visited.add(new ArrayList<Boolean>(c));
        }
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                visited.get(i).add(j, false);
            }
        }

        int islandsCount = 0;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(!(visited.get(i).get(j)) && v.get(i).get(j) == 'L') {
                    dfs(i,j);
                    islandsCount++;
                }
            }
        }

        io.println(islandsCount); 
        io.close();
    }

    public static int r;
    public static int c;

    public static ArrayList<ArrayList<Character>> v;
    public static ArrayList<ArrayList<Boolean>> visited;

    public static List<Integer> dx = Arrays.asList(new Integer[] {1,-1,0,0});
    public static List<Integer> dy = Arrays.asList(new Integer[] {0,0,1,-1});

    public static void dfs(int i, int j) {

        if(visited.get(i).get(j)) return;


        visited.get(i).set(j, true);

        for(int k = 0; k < 4; k++) {

            int ri = i + dx.get(k);
            int rj = j + dy.get(k);

            if(ri < 0) continue;
            if(rj < 0) continue;
            if(ri >= r) continue;
            if(rj >= c) continue;

            if(v.get(ri).get(rj) == 'L' || v.get(ri).get(rj) == 'C') {
                dfs(ri,rj);
            }
        }

    }


}
