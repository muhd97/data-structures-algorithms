public class Quest implements Comparable<Quest> {
	public int e; 
	public int g;
			
	public int getE() {
		return e;
	}	
	public int getG() {
		return g;
	}
	public Quest(int e, int g) {
		this.e = e;
		this.g = g;
	}
	
	@Override 
	public int compareTo(Quest o) {
		if (this.e == o.e) {
			return -(this.g - o.g);			
		}
		else {
			return -(this.e - o.e);
		}		
	}
}
