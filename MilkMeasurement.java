import java.util.*;
import java.io.*;
public class MilkMeasurement {

	public static boolean submit = true;
	public static String fileName = "measurement";

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		if (submit) {
			s = new Scanner(new File(fileName + ".in"));
			pw = new PrintWriter(new FileWriter(fileName + ".out"));
		}
		MilkMeasurement mm = new MilkMeasurement();
		mm.run(s, pw);
		pw.close();
	}

	public void run(Scanner s, PrintWriter pw) {
		int n = s.nextInt();
		Event[] evt = new Event[n];
		for (int i = 0; i < n; i++) {
			evt[i] = new Event(s.nextInt(), s.next(), s.nextInt());
		}

		Arrays.sort(evt);

		HashMap<String,Integer> map = new HashMap<>();

		map.put("Bessie",0);
		map.put("Mildred",1);
		map.put("Elsie",2);

		int changes = 0;
		int[] milk = new int[3];
		boolean[] isTop = {true, true, true};
		for (int i = 0; i < n; i++) {
			int index = map.get(evt[i].cow);
			milk[index] += evt[i].diff;
			int max = milk[0];
			for (int j = 0; j < 3; j++) 
				max = Math.max(milk[j], max);
			boolean[] newTop = new boolean[3];
			for (int j = 0; j < 3; j++)
				newTop[j] = (milk[j] == max);
			if (!Arrays.equals(isTop, newTop)) {
				changes++;
				isTop = newTop;
			}
		}
		pw.print(changes);
	}
}

class Event implements Comparable<Event>{
	
	int day;
	String cow;
	int diff;

	public Event(int d, String c, int df) {
		day = d;
		cow = c;
		diff = df;
	}

	public int compareTo(Event e) { return day - e.day; }
}