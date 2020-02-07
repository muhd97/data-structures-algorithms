import java.util.*;
import java.io.*;

public class Map{

    int V; // no of vertices
    int E; // no of edges
    ArrayList<Edge> edgeList; // stores all the edges of the map

    // Creates a map with V vertices and E edges
    Map(int v, int e){
        this.V = v;
        this.E = e;
        this.edgeList = new ArrayList<>();
        for (int i = 0; i < e; i++){
            this.edgeList.add(new Edge());
        }
    
    }

    // construct MST using Kruskal's algorithm
    void KruskalMST(){
        ArrayList<Edge> mst = new ArrayList<>();  // This is the resultant MST
        int e = 0;  // counter for mst
        int i = 0;  // counter for edges in the sorted edgeList
        for (i = 0; i < V; i++){
            mst.add(new Edge());
        }

        // Sort all the edges in ascending order by their weight
        Collections.sort(edgeList);

        // create V disjoint sets
        UnionFind UF = new UnionFind(V);

        i = 0;  // pick the next edge from the start 

        // Number of edges required are V-1
        while (e < V - 1){
            // Pick the smallest edge then increase the counter i
            Edge nextEdge = new Edge();
            nextEdge = edgeList.get(i);
            i++;

            int s = nextEdge.src;
            int d = nextEdge.dest;

            // If including this edge causes no cycle, include it in mst and increase the counter e
            if (!UF.isSameSet(s, d)){
                mst.set(e, nextEdge);
                e++;
                UF.unionSet( s, d);
            }
            // Else skip this nextEdge
        }

        for (i = 0; i < e; i++)
            System.out.println((mst.get(i).src + 1) + " " + (mst.get(i).dest + 1));
    }

    public static void main (String[] args){
        int n;
        Kattio k = new Kattio(System.in, System.out);
        n = k.getInt();

        Map m = new Map(n, n*n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m.edgeList.get(j+i*n).src = i;
                m.edgeList.get(j+i*n).dest = j;
                m.edgeList.get(j+i*n).weight = k.getInt();
            }
        }

        m.KruskalMST();
    }
}


class Edge implements Comparable<Edge>{
    int src, dest, weight;

    // Comparator function used for sorting edges based on their weight
    public int compareTo(Edge o){
        return this.weight - o.weight;
    }

}

// Union-Find Disjoint Sets Library, using both path compression and union by rank heuristics
class UnionFind {
    public ArrayList<Integer> p, rank, setSize;
    public int numSets;

    public UnionFind(int N) {
        p = new ArrayList<Integer>(N);
        rank = new ArrayList<Integer>(N);
        setSize = new ArrayList<Integer>(N);
        numSets = N;
        for (int i = 0; i < N; i++) {
            p.add(i);
            rank.add(0);
            setSize.add(1);
        }
    }

    public int findSet(int i) { 
        if (p.get(i) == i) return i;
        else {
            int ret = findSet(p.get(i)); p.set(i, ret);
            return ret; 
        } 
    }

    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

    public void unionSet(int i, int j) { 
        if (!isSameSet(i, j)) { 
            numSets--; 
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
            else                           { p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
                if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y)+1); } 
        } 
    }

    public int numDisjointSets() {
        return numSets; 
    }

    public int sizeOfSet(int i) {
        return setSize.get(findSet(i)); 
    }

}

/** Simple yet moderately fast I/O routines.
 *
 * Example usage:
 *
 * Kattio io = new Kattio(System.in, System.out);
 *
 * while (io.hasMoreTokens()) {
 *    int n = io.getInt();
 *    double d = io.getDouble();
 *    double ans = d*n;
 *
 *    io.println("Answer: " + ans);
 * }
 *
 * io.close();
 *
 *
 * Some notes:
 *
 * - When done, you should always do io.close() or io.flush() on the
 *   Kattio-instance, otherwise, you may lose output.
 *
 * - The getInt(), getDouble(), and getLong() methods will throw an
 *   exception if there is no more data in the input, so it is generally
 *   a good idea to use hasMoreTokens() to check for end-of-file.
 *
 * @author: Kattis
 */
class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
