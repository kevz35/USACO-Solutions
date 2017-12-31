import java.util.*;
import java.io.*;
public class TheBovineShuffle2v2 {

	public static boolean submit = true;
	public static String fileName = "shuffle";

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		if (submit) {
			s = new Scanner(new File(fileName + ".in"));
			pw = new PrintWriter(new FileWriter(fileName + ".out"));
		}
		TheBovineShuffle2 tbs2 = new TheBovineShuffle2();
		tbs2.run(s, pw);
		pw.close();
	}

	public void run(Scanner s, PrintWriter pw) {
		int n = s.nextInt();
		int total = 0;
		int[] arr = new int[n];
		int[] time = new int[n];
		int[] loops = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt() - 1;
			loops[i] = -1;
		}

		for (int i = 0; i < n; i++) {
			int cow = i;
			int numCows = 0;
			while (loops[cow] == -1) {
				loops[cow] = i;
				time[cow] = numCows;
				cow = arr[cow];
				numCows++;
			}
			if (loops[cow] == i) {
				total += numCows - time[cow];
			}
		}

		pw.print(total);
	}

}