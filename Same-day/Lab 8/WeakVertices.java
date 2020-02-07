public class WeakVertices{

    public static void main(String[] args) {

        Kattio k = new Kattio(System.in, System.out);

        while (true){
            int n = k.getInt();

            if (n == -1){
                break;
            }

            int[][] adj = new int[n][n];

            for (int r = 0; r < n; r++){
                for (int c = 0; c < n; c++){
                    adj[r][c] = k.getInt();
                }
            }

            for (int r = 0; r < n; r++){
                if (formsTriangle(r , adj) == false){ 
                    k.print(r + " ");
                }else{
                    continue;
                }

            }
            k.println();
        }
        k.flush();
    }


    public static boolean formsTriangle(int r , int[][] adj){

        for (int i = 0; i < adj.length; i++){
            if (adj[r][i] == 1){
                for (int j = i + 1; j < adj.length; j++){
                    if (adj[i][j] == 1 && adj[r][j] == 1){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
