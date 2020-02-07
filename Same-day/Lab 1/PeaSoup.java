import java.util.Scanner;

public class PeaSoup {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int restaurants = sc.nextInt();
		String winningRestaurant = "";
		
		while (restaurants-- > 0) {
			int numItems = sc.nextInt();
			sc.nextLine(); // consume newline
			
			String restName = sc.nextLine();
			boolean hasPeaSoup = false; //reset for every restaurant
			boolean hasPancakes = false;
			
			while(numItems-- > 0) {
				String item = sc.nextLine();
				if (item.equals("pea soup"))
					hasPeaSoup = true;
				else if (item.equals("pancakes"))
					hasPancakes = true;
			}
			
			// stop on the first restaurant that has both
			if (hasPeaSoup && hasPancakes) {
				winningRestaurant = restName;
				break;
			}
		}
		
		if (winningRestaurant.isEmpty()) {
			winningRestaurant = "Anywhere is fine I guess";
		}
		
		System.out.println(winningRestaurant);

	}

}
