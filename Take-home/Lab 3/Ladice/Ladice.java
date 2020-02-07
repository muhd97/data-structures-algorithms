import java.util.*;

class UnionFind{
    public int[] parent;
    public int[] rank;

    public UnionFind(int N){
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int findSet(int i) { 
        if (parent[i] == i) return i;
        else {
            parent[i] = findSet(parent[i]);
            return parent[i]; 
        } 
    }

    public boolean isSameSet(int i, int j){
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j){
        int p = findSet(i), q = findSet(j);

        if (p == q) return;    
        if (rank[p] < rank[q]) {
            parent[p] = q;			
        } else if (rank[p] > rank[q]){
            parent[q] = p;
        } else {
            parent[q] = p;
            rank[q]++;
        }
        return;
    }
}

public class Ladice{

    public static void main(String[] args){

        Kattio k = new Kattio(System.in, System.out);
        int n = k.getInt(); 
        int l = k.getInt();
        UnionFind u = new UnionFind(l+1);

        int[] emptyDrawers = new int[l+1];
        for( int i = 0; i< emptyDrawers.length ; i++){
            emptyDrawers[i] = 1;
        }

        for (int i = 0; i < n; i++){
            int a = k.getInt();
            int b = k.getInt();

            if (u.isSameSet(a, b) == false ){
                int total = emptyDrawers[u.findSet(a)] + emptyDrawers[u.findSet(b)];
                u.unionSet(a, b);
                emptyDrawers[u.findSet(a)] = total;
            }
            if (emptyDrawers[u.findSet(a)] > 0){
                emptyDrawers[u.findSet(a)]--;
                k.println("LADICA");
            }
            else{
                k.println("SMECE");
            }
        }
        k.close();
    }
}
