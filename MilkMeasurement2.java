import java.util.*;
import java.io.*;
public class MilkMeasurement2 {

	public static boolean submit = true;
	public static String fileName = "measurement";

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		if (submit) {
			s = new Scanner(new File(fileName + ".in"));
			pw = new PrintWriter(new FileWriter(fileName + ".out"));
		}
		MilkMeasurement2 mm2 = new MilkMeasurement2();
		mm2.run(s, pw);
		pw.close();
	}

	public void run(Scanner s, PrintWriter pw) {
		int n = s.nextInt();
		int g = s.nextInt();
		Event[] evt = new Event[n];
		for (int i = 0; i < n; i++) {
			evt[i] = new Event(s.nextInt(), s.nextInt(), s.nextInt());
		}

		Arrays.sort(evt);

		HashMap<Integer,Integer> freq = new HashMap<>();
		freq.put(g, n + 2);

		HashMap<Integer,Integer> uniq = new HashMap<>();

		TreeSet<Integer> set = new TreeSet<>();
		set.add(g);

		int changes = 0;
		int prevMax = g;
		int prevNumCows = n+2;

		for (int i = 0; i < n; i++) {
			Integer cow = uniq.get(evt[i].cow);

			if (cow == null)
				cow = g;
			Integer temp = freq.get(cow);
			freq.put(cow, temp-1);
			if (temp == 1)
				set.remove(cow);

			cow += evt[i].diff;

			temp = freq.get(cow);
			if (temp == null || temp == 0) {
				freq.put(cow, 1);
				set.add(cow);
			}
			else
				freq.put(cow, temp+1);

			int newMax = set.last();
			int numCows = freq.get(newMax);

			if (numCows != prevNumCows) {
				changes++;
			}
			else if (newMax != prevMax) {
				if (numCows == 1 && prevNumCows == 1) {
					if (freq.get(prevMax) == 0 && cow == newMax) {}
					else {
						changes++;
					}
				}
			}

			prevMax = newMax;
			prevNumCows = numCows;
			uniq.put(evt[i].cow, cow);
		}
		pw.print(changes);
	}

}

class Event implements Comparable<Event>{
	
	int day, cow, diff;

	public Event(int d, int c, int df) {
		day = d;
		cow = c;
		diff = df;
	}

	public int compareTo(Event other) { return day - other.day; }

}