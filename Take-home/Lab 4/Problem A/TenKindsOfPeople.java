import java.util.*;

public class TenKindsOfPeople{

    public static int rows;
    public static int columns; 
    public static int[][] map = new int[1000][1000];
    public static boolean[][] visited = new boolean[1000][1000];
    public static int[] dx = { 0, 0, -1, 1 };
    public static int[] dy = { 1, -1, 0, 0 };
    public static int[] Hashes = new int[1000000];

    public static void bfs(int r, int c){

        Queue<Location> q = new LinkedList<Location>(); 
        q.offer(new Location(r, c));
        visited[r][c] = true;
        int h = hashFunc(r, c);
        Hashes[h] = h;

        while (!q.isEmpty()) {
            Location curr = q.poll();
            int r1 = curr.row;
            int c1 = curr.column;

            for (int x = 0; x < 4; x++) {
                int r2 = r1 + dx[x];
                int c2 = c1 + dy[x];
                if (r2 < 0 || c2 < 0 || r2 >= rows || c2 >= columns || visited[r2][c2] || map[r2][c2] != map[r][c]){
                    continue;
                }

                visited[r2][c2] = true;
                Hashes[ hashFunc(r2, c2) ] = h;
                q.offer(new Location(r2, c2 ));
            }
        }
    }

    public static int hashFunc(int a, int b) { //https://stackoverflow.com/questions/11742593/what-is-the-hashFunccode-for-a-custom-class-having-just-two-int-properties
        return a < b ? b * b + a : a * a + a + b;
    }

    public static void main(String[] args) throws Exception{

        Kattio k = new Kattio(System.in, System.out);
        rows = k.getInt(); 
        columns = k.getInt(); 

        for (int r = 0; r < rows; r++){
            String line = k.r.readLine();
            for(int c=0; c<columns; c++){
                map[r][c] = Character.getNumericValue(line.charAt(c));
            }
        }


        for (int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                if (!visited[r][c]){
                    bfs(r, c);
                }
            }
        }

        int n = k.getInt(); 
        String result = "";

        for(int query = 0; query  < n; query++ ){
            int r1 = k.getInt() -1; 
            int c1 = k.getInt() -1; 
            int r2 = k.getInt() -1; 
            int c2 = k.getInt() -1; 

            boolean  reachable = Hashes[ hashFunc(r1, c1) ] == Hashes [ hashFunc(r2, c2) ];
            if(!reachable){
                result += "neither";
            }else{
                int c = map[r1][c1]; 
                if(c == 0){ 
                    result += "binary";
                }else{
                    result += "decimal";
                }

            }
            result += '\n';
        }
        k.println(result);
        k.flush();
    }

}


