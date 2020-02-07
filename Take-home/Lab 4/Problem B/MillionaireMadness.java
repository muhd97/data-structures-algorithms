import java.util.*;

//use Dijkstra, replacing minimum distance with minimum ladder

public class MillionaireMadness{

    public static int[][] locations;
    public static boolean[][] visited = new boolean[1000][1000];

    public static void main(String[] args) {

        Kattio k = new Kattio (System.in, System.out);

        // Read the grid into the locations, initialise visited to false.
        int m = k.getInt();
        int n = k.getInt();

        locations = new int[m][n];

        for( int x = 0; x < m; x++ ){
            for( int y = 0; y < n; y++){
                locations[x][y] = k.getInt();
                visited[x][y] = false;
            }
        }

        k.println(Dijkstra(m, n, locations));

        k.flush(); 
    }



    // The position at the front of the queue is the next position that is easiest to reach.

    public static int Dijkstra(int m, int n, int[][] locations){

        PriorityQueue<Position> Q = new PriorityQueue<Position>(); // contains ladder required, and position
        Position p = new Position(0, 0, 0);
        Q.add(p);

        int ladder = 0;

        while(!Q.isEmpty()){
            p = Q.remove();
            int i = p.x;
            int j = p.y;
            ladder = Math.max(ladder, p.ladder);

            // If we reach the end, we are done; ladder is the answer.

            if(i == m-1 && j == n-1){
                return ladder;
            }


            // If we have visited the position before, continue.
            if(visited[i][j]){
                continue;
            }

            visited[i][j]= true;

            // Add all the neighbours of the current position as visited.
            for( int x = -1; x <= 1; x ++){
                for( int y = -1; y <= 1; y ++){

                    if((x == 0) == (y == 0)){
                        continue;
                    }

                    if(i + x >= m || j + y >= n ||  i + x < 0  || j + y < 0 ){
                        continue;
                    }

                                                //ladder
                    Position np = new Position(locations[i+x][j+y] - locations[i][j], i+x, j+y);
                    Q.add(np);
                }
            }
        }
        return -1;
    }
}

class Position implements Comparable<Position>{
    int ladder;
    int x;
    int y;

    public Position(int ladder, int x, int y ){
        this.x = x;
        this.y = y;
        this.ladder = ladder;
    }

    @Override
        public int compareTo( Position other ){
            return this.ladder - other.ladder;
        }
}

