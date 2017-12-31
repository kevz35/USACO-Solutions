import java.util.*;
import java.io.*;
public class TheBovineShuffle {

	public static boolean submit = true;
	public static String fileName = "shuffle";

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		if (submit) {
			s = new Scanner(new File(fileName + ".in"));
			pw = new PrintWriter(new FileWriter(fileName + ".out"));
		}
		TheBovineShuffle tbs = new TheBovineShuffle();
		tbs.run(s, pw);
		pw.close();
	}

	public void run(Scanner s, PrintWriter pw) {
		int n = s.nextInt();
		int[] arr = new int[n];
		int[] pos = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt() - 1;
			pos[i] = i;
		}

		String[] names = new String[n];
		for (int i = 0; i < n; i++) {
			names[i] = s.next();
		}

		for (int i = 0; i < 3; i++) {
			int[] newPos = new int[n];
			for (int j = 0; j < n; j++) {
				newPos[arr[j]] = pos[j];
			}
			pos = newPos;
		}

		String[] newNames = new String[n];
		for (int i = 0; i < n; i++) {
			newNames[pos[i]] = names[i];
		}

		for (int i = 0; i < n; i++) {
			pw.println(newNames[i]);
		}
	}
}