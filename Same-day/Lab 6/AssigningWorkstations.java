import java.util.*;
import java.io.*;

public class AssigningWorkstations {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {

    // Read first line of input
    String[] line = br.readLine().split(" ");
    int n = Integer.valueOf(line[0]);
    int m = Integer.valueOf(line[1]);

    // Setup
    Queue<Event> researchers = new PriorityQueue<>();

    // Read in researchers
    for (int i = 0; i < n; i++) {
      line = br.readLine().split(" ");
      int a = Integer.valueOf(line[0]);
      int s = Integer.valueOf(line[1]);
      researchers.add(new Arrival(i, a, a + s));
      researchers.add(new Departure(i, a, a + s));
    }

    // Count the unlockings saved
    Queue<Event> availableWorkstations = new LinkedList<>();
    int unlockingsSaved = 0;
    while (!researchers.isEmpty()) {

      Event researcher = researchers.poll();

      // Arrival
      if (researcher instanceof Arrival) {

        // Look for unlocked workstation
        while (!availableWorkstations.isEmpty()) {
          Event workstation = availableWorkstations.poll();
          if (researcher.arrive - workstation.leave <= m) {
            unlockingsSaved++;
            break;
          }
        }

        // Departure
      } else {

        availableWorkstations.offer(researcher);

      }

    }

    // Output answer
    System.out.println(unlockingsSaved);

  }

}

abstract class Event implements Comparable<Event> {

  int id,
  arrive,
  leave;

  public Event(int id, int arrive, int leave) {
    this.id = id;
    this.arrive = arrive;
    this.leave = leave;
  }

  public abstract int getTime();
  public abstract int getPriority();

  // Compare the events (putting departures before arrivals)
  @Override public int compareTo(Event other) {
    int cmp = Integer.compare(getTime(), other.getTime());
    if (cmp == 0)
      return Integer.compare(getPriority(), other.getPriority());
    return cmp;
  }

}

class Arrival extends Event {

  public Arrival(int id, int arrive, int leave) {
    super(id, arrive, leave);
  }

  public int getTime() {
    return arrive;
  }

  public int getPriority() {
    return 2;
  }

}

class Departure extends Event {

  public Departure(int id, int arrive, int leave) {
    super(id, arrive, leave);
  }

  public int getTime() {
    return leave;
  }

  public int getPriority() {
    return 1;
  }

}