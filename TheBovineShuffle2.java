import java.util.*;
import java.io.*;
public class TheBovineShuffle2 {

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
		int total = n;
		int[] in = new int[n];
		int[] out = new int[n];

		for (int i = 0; i < n; i++) {
			out[i] = s.nextInt() - 1;
			in[out[i]]++;
		}

		Stack<Integer> stack = new Stack();
		for (int i = 0; i < n; i++) {
			if (in[i] == 0)
				stack.push(i);
		}

		while (!stack.isEmpty()) {
			int cow = stack.pop();
			in[out[cow]]--;
			total--;
			if (in[out[cow]] == 0) {
				stack.push(out[cow]);
			}
		}
		
		pw.print(total);
	}

}