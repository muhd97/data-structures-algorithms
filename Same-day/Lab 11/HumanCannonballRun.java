import java.util.*;

public class HumanCannonballRun{

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Get start and end.
		double[] start = new double[2];
		double[] end = new double[2];
		for (int i=0; i<2; i++)
			start[i] = sc.nextDouble();
		for (int i=0; i<2; i++)
			end[i] = sc.nextDouble();

		// Get # of cannons.
		int n = sc.nextInt();

		// Read in pts to indexes 1 to n.
		double[][] pts = new double[n+2][2];
		for (int i=1; i<=n; i++)
			for (int j=0; j<2; j++)
				pts[i][j] = sc.nextDouble();

		// Put start at 0, end at n+1.
		pts[0] = start;
		pts[n+1] = end;

		// Set up time for running and our "infinty"
		double runAll = dist(pts[0], pts[n+1])/5;
		double inf = runAll+1;

		// Initialize empty adjacency matrix.
		double[][] adj = new double[n+2][n+2];
		for (int i=0; i<n+2; i++) {
			Arrays.fill(adj[i], inf);
			adj[i][i] = 0;
		}

		// Fill in distances for running from start to everywhere else.
		for (int i=1; i<n+2; i++)
			adj[0][i] = dist(pts[0], pts[i])/5;

		// Distances for launching from a cannon and then running to the destination. Also, includes
		// just running...whichever is better.
		for (int i=1; i<n+1; i++)
			for (int j=1; j<n+2; j++)
				adj[i][j] = Math.min(2 + Math.abs(dist(pts[i], pts[j])-50)/5, dist(pts[i], pts[j])/5);

		// I am lazy - due to the bounds, I can run Floyd's.
		for (int k=0; k<n+2; k++)
			for (int i=0; i<n+2; i++)
				for (int j=0; j<n+2; j++)
					adj[i][j] = Math.min(adj[i][j], adj[i][k]+adj[k][j]);

		// Output result.
		System.out.printf("%n$%.6f",adj[0][n+1]);
	}

	public static double dist(double[] a, double[] b) {
		return Math.sqrt((a[0]-b[0])*(a[0]-b[0]) + (a[1]-b[1])*(a[1]-b[1]));
	}
}
