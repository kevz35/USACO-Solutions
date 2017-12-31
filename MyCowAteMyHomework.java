import java.util.*;
import java.io.*;
public class MyCowAteMyHomework {
	
	public static boolean submit = true;
	public static String fileName = "homework";

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		if (submit) {
			s = new Scanner(new File(fileName + ".in"));
			pw = new PrintWriter(new FileWriter(fileName + ".out"));
		}
		MyCowAteMyHomework mcamh = new MyCowAteMyHomework();
		mcamh.run(s, pw);
		pw.close();
	}

	public void run(Scanner s, PrintWriter pw) {
		int n = s.nextInt();
		int[] scores = new int[n];
		int total = 0;
		for (int i = 0; i < n; i++) {
			scores[i] = s.nextInt();
			total += scores[i];
		}

		int[] mins = new int[n];
		int min = scores[n-1];
		for (int i = n-1; i >= 0; i--) {
			if (scores[i] < min) {
				min = scores[i];
			}
			mins[i] = min;
		}
		
		double max = 0.0;
		double[] ans = new double[n-2];

		for (int k = 0; k < n-2; k++) {
			total -= scores[k];
			ans[k] = (total - mins[k])/(n - (k + 2.0));
			if (ans[k] > max) {
				max = ans[k];
			}
		}

		for (int k = 0; k < n-2; k++) {
			if (ans[k] == max) {
				pw.println(k+1);
			}
		}

	}

}